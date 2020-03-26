package com.writerxl.api.data.service;

import com.writerxl.api.data.mapper.GroupMapper;
import com.writerxl.api.data.model.GroupEntity;
import com.writerxl.api.data.repository.GroupRepository;
import com.writerxl.api.exception.GroupNotFoundException;
import com.writerxl.api.model.Group;
import com.writerxl.api.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    @Override
    public Group getGroupById(int id) {
        return GroupMapper.MAP.toModel(groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Unable to find user with the specified key.")
        ));
    }

    @Override
    public Group createGroup(Group group) {
        return GroupMapper.MAP.toModel(saveGroup(GroupMapper.MAP.toEntity(group)));
    }

    @Override
    public Group updateGroup(Group group) {
        getGroupById(group.getId());
        return GroupMapper.MAP.toModel(saveGroup(GroupMapper.MAP.toEntity(group)));
    }

    private GroupEntity saveGroup(GroupEntity groupToSave) {
        return groupRepository.save(groupToSave);
    }
}
