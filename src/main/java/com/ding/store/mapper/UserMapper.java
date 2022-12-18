package com.ding.store.mapper;


import com.ding.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from t_user")
    List<User> list();

    Integer updatePassword(@Param("uid")Integer uid,
                           @Param("password")String password,
                           @Param("modifiedUser")String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime
                           );

    Integer insertUser(User user);

    /**
     * 修改用户数据
     * @param user
     * @return
     */
    Integer updateInfo(User user);



    @Select("select * from t_user where uid = #{uid}")
    User findUserById(Integer uid);

    @Select("select * from t_user where username = #{userName}")
    User findByUserName(String userName);


    Integer updateAvatar(
                         @Param("uid")Integer uid,
                         @Param("avatar")String avatar,
                         @Param("modifiedUser")String modifiedUser,
                         @Param("modifiedTime") Date modifiedTime
                         );

}
