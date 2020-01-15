package com.writerxl.api.data.repository;

import com.writerxl.api.data.model.JpaUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends CrudRepository<JpaUser, Integer> {

    Optional<JpaUser> findOneByUserKey(final String userKey);

    Optional<JpaUser> findOneByEmailAddress(final String emailAddress);

}
