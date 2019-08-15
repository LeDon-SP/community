package life.ledon.community.mapper;

import life.ledon.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,avatar_url,email,gmt_create,gmt_modified) values (#{name},#{account_id},#{token},#{avatar_url},#{email},#{gmt_create},#{gmt_modified})")
    public void insert(User user);
}
