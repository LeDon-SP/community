package life.ledon.community.service;

import life.ledon.community.dto.QuestionDTO;
import life.ledon.community.mapper.QuestionMapper;
import life.ledon.community.mapper.UserMapper;
import life.ledon.community.model.Question;
import life.ledon.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
//        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        GregorianCalendar gc = new GregorianCalendar();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            //gc.setTimeInMillis(question.getGmt_create());
            //questionDTO.setGmt_create(dateformat.format(gc.getTime()));
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
