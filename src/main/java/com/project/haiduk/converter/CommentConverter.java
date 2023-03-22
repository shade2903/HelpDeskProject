package com.project.haiduk.converter;

import com.project.haiduk.domain.Comment;
import com.project.haiduk.dto.CommentDto;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter extends AbstractConverter<Comment, CommentDto> {
    @Override
    Class<CommentDto> getDomainClass() {
        return CommentDto.class;
    }

    @Override
    Class<Comment> getEntityClass() {
        return Comment.class;
    }
}
