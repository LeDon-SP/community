package life.ledon.community.mapper;

import life.ledon.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Insert("insert into user (name,account_id,token,avatar_url,email,gmt_create,gmt_modified) values (#{name},#{account_id},#{token},#{avatar_url},#{email},#{gmt_create},#{gmt_modified})")
    void insert(User user);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id=#{account_id}")
    User findByAccountId(@Param("account_id") String account_id);

    @Update("update user set name=#{name},account_id=#{account_id},token=#{token},avatar_url=#{avatar_url},gmt_modified=#{gmt_modified} where id=#{id}")
    void update(User user);
}
