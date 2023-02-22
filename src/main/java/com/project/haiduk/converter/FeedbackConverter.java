package com.project.haiduk.converter;

import com.project.haiduk.domain.Feedback;
import com.project.haiduk.dto.FeedbackDto;

public class FeedbackConverter extends AbstractConverter<Feedback, FeedbackDto> {
    @Override
    Class<FeedbackDto> getDomainClass() {
        return FeedbackDto.class;
    }

    @Override
    Class<Feedback> getEntityClass() {
        return Feedback.class;
    }
}
