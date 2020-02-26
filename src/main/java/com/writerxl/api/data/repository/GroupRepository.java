package com.writerxl.api.data.repository;

import com.writerxl.api.data.model.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Integer> {

}
