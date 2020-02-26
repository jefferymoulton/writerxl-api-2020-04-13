package com.writerxl.api.data.mapper;

import com.writerxl.api.data.model.GroupEntity;
import com.writerxl.api.model.Group;
import com.writerxl.api.model.request.GroupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupMapper {

    GroupMapper MAPPER = Mappers.getMapper(GroupMapper.class);

    Group toModel(GroupEntity groupEntity);

    GroupEntity toEntity(Group group);

    GroupEntity toRequestEntity(GroupRequest groupRequest);

}
