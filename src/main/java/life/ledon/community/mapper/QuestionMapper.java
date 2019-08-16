package life.ledon.community.mapper;

import life.ledon.community.model.Question;
import org.apache.ibatis.annotations.Insert;

public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void create(Question qustion);
}
