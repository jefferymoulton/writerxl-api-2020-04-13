package com.writerxl.api.data.service;

import com.writerxl.api.data.mapper.JpaGroupMapper;
import com.writerxl.api.data.model.JpaGroup;
import com.writerxl.api.data.repository.JpaGroupRepository;
import com.writerxl.api.exception.GroupNotFoundException;
import com.writerxl.api.model.Group;
import com.writerxl.api.model.request.GroupRequest;
import com.writerxl.api.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JpaGroupService implements GroupService {

    private JpaGroupRepository groupRepository;

    @Override
    public Group getGroupById(int id) throws GroupNotFoundException {
        return JpaGroupMapper.MAPPER.toModel(groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Unable to find user with the specified key.")
        ));
    }

    @Override
    public Group createGroup(GroupRequest groupRequest) {
        return JpaGroupMapper.MAPPER.toModel(saveGroup(JpaGroupMapper.MAPPER.toRequestEntity(groupRequest)));
    }

    @Override
    public Group updateGroup(Group group) throws GroupNotFoundException {
        getGroupById(group.getId());
        return JpaGroupMapper.MAPPER.toModel(saveGroup(JpaGroupMapper.MAPPER.toEntity(group)));
    }

    private JpaGroup saveGroup(JpaGroup groupToSave) {
        return groupRepository.save(groupToSave);
    }
}
