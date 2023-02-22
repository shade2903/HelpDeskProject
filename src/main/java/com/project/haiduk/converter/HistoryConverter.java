package com.project.haiduk.converter;

import com.project.haiduk.domain.History;
import com.project.haiduk.dto.HistoryDto;

public class HistoryConverter extends AbstractConverter<History, HistoryDto> {
    @Override
    Class<HistoryDto> getDomainClass() {
        return HistoryDto.class;
    }

    @Override
    Class<History> getEntityClass() {
        return History.class;
    }
}
