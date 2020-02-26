package com.writerxl.api.data.repository;

import com.writerxl.api.data.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findOneByUserKey(final String userKey);

    Optional<UserEntity> findOneByEmail(final String email);

}
