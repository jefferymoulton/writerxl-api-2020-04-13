package com.writerxl.api.data.mapper;

import com.writerxl.api.data.model.UserEntity;
import com.writerxl.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    User toModel(UserEntity userEntity);

    UserEntity mapEntity(User user);

}
