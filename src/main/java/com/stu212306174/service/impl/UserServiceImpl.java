package com.stu212306174.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu212306174.common.Result;
import com.stu212306174.common.ResultCode;
import com.stu212306174.dto.UserDTO;
import com.stu212306174.entity.User;
import com.stu212306174.mapper.UserMapper;
import com.stu212306174.service.UserService;
import com.stu212306174.vo.UserDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 用你已经熟悉的 @Autowired，不再用 @Resource，避免导入问题
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final String KEY_PREFIX = "user:detail:";

    @Override
    public Result<String> register(UserDTO userDTO) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, userDTO.getUsername());
        User exist = userMapper.selectOne(wrapper);

        if (exist != null) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userMapper.insert(user);

        return Result.success("注册成功！");
    }

    @Override
    public Result<String> login(UserDTO userDTO) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, userDTO.getUsername());
        User user = userMapper.selectOne(wrapper);

        if (user == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        if (!user.getPassword().equals(userDTO.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }
        return Result.success("登录成功");
    }

    @Override
    public Result<String> getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        return Result.success("查询成功，用户：" + user.getUsername());
    }

    @Override
    public Result<Object> getUserPage(Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        Page<User> resultPage = userMapper.selectPage(page, null);
        return Result.success(resultPage);
    }

    @Override
    public Result<UserDetailVO> getUserDetail(Long userId) {
        // 1. 查 Redis 缓存
        String key = KEY_PREFIX + userId;
        String json = stringRedisTemplate.opsForValue().get(key);

        if (json != null) {
            UserDetailVO vo = JSON.parseObject(json, UserDetailVO.class);
            return Result.success(vo);
        }

        // 2. 从数据库查用户信息
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }

        // 3. 组装VO对象
        UserDetailVO vo = new UserDetailVO();
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setRealName("系统用户");
        vo.setPhone("未设置");
        vo.setAddress("未设置");

        // 4. 存入Redis，设置10分钟过期
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(vo), 10, TimeUnit.MINUTES);

        return Result.success(vo);
    }
}