package life.ledon.community.mapper;

import life.ledon.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment record);
}