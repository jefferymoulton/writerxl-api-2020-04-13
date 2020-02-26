package com.writerxl.api.data.mapper;

import com.writerxl.api.data.model.JpaGroup;
import com.writerxl.api.model.Group;
import com.writerxl.api.model.request.GroupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JpaGroupMapper {

    JpaGroupMapper MAPPER = Mappers.getMapper(JpaGroupMapper.class);

    Group toModel(JpaGroup jpaGroup);

    JpaGroup toEntity(Group group);

    JpaGroup toRequestEntity(GroupRequest groupRequest);

}
