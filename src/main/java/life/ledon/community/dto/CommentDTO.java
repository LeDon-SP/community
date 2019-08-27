package life.ledon.community.dto;

import life.ledon.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;

    private Long parentId;

    private Long commentator;

    private Integer type;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private String content;

    private User user;
}
