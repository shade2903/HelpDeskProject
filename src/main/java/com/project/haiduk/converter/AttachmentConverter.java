package com.project.haiduk.converter;

import com.project.haiduk.domain.Attachment;
import com.project.haiduk.dto.AttachmentDto;

public class AttachmentConverter extends AbstractConverter<Attachment, AttachmentDto> {
    @Override
    Class<AttachmentDto> getDomainClass() {
        return AttachmentDto.class;
    }

    @Override
    Class<Attachment> getEntityClass() {
        return Attachment.class;
    }
}
