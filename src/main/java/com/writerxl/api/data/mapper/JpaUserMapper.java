package com.writerxl.api.data.mapper;

import com.writerxl.api.data.model.JpaUser;
import com.writerxl.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JpaUserMapper {

    JpaUserMapper MAPPER = Mappers.getMapper(JpaUserMapper.class);

    User toModel(JpaUser jpaUser);

    JpaUser mapEntity(User user);

}
