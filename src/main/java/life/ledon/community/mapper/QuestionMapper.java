package life.ledon.community.mapper;

import life.ledon.community.dto.QuestionDTO;
import life.ledon.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void create(Question qustion);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{user_id} limit #{offset},#{size}")
    List<Question> listByUserId(@Param("user_id") Integer userId,@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("select count(1) from question where creator = #{user_id}")
    Integer countByUserId(@Param("user_id") Integer userId);

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);
}
