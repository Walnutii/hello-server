package com.stu212306174.mapper;

import com.stu212306174.vo.UserDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {

    /**
     * 多表联查用户详情
     * @param userId 用户ID
     * @return 包含sys_user和user_info字段的VO对象
     */
    @Select("""
            SELECT
                u.id AS userId,
                u.username,
                i.real_name AS realName,
                i.phone,
                i.address
            FROM sys_user u
            LEFT JOIN user_info i ON u.id = i.user_id
            WHERE u.id = #{userId}
            """)
    UserDetailVO getUserDetail(@Param("userId") Long userId);
}