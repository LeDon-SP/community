package life.ledon.community.mapper;

import life.ledon.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Insert("insert into user (name,account_id,token,avatar_url,email,gmt_create,gmt_modified) values (#{name},#{account_id},#{token},#{avatar_url},#{email},#{gmt_create},#{gmt_modified})")
    void insert(User user);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);
}
