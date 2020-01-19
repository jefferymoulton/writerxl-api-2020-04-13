package com.writerxl.api.data.repository;

import com.writerxl.api.data.model.JpaGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaGroupRepository extends CrudRepository<JpaGroup, Integer> {

}
