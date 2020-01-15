package com.writerxl.api.data.mapper;

import com.writerxl.api.data.model.JpaUser;
import com.writerxl.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JpaUserMapper {

    JpaUserMapper MAPPER = Mappers.getMapper(JpaUserMapper.class);

    User map(JpaUser jpaUser);

    JpaUser mapDb(User user);

    List<User> mapList(List<JpaUser> jpaUsers);

    List<JpaUser> mapDb(List<User> users);

}
