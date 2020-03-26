package com.writerxl.api.data.service;

import com.writerxl.api.data.model.GroupEntity;
import com.writerxl.api.data.repository.GroupRepository;
import com.writerxl.api.exception.GroupNotFoundException;
import com.writerxl.api.model.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {

    private GroupServiceImpl groupService;

    @Mock
    private GroupRepository groupRepository;

    private static final int VALID_GROUP_ID = 12345;
    private static final String VALID_GROUP_NAME = "Valid Group";
    private static final String VALID_GROUP_DESCRIPTION = "This is a valid group";

    private static final String UPDATE_GROUP_NAME = "Updated Group";
    private static final String UPDATE_GROUP_DESCRIPTION = "updated description";

    private static final int INVALID_GROUP_ID = 98765;

    private final Group validGroup = new Group(
            VALID_GROUP_NAME,
            VALID_GROUP_DESCRIPTION
    );

    private final GroupEntity validGroupEntity = new GroupEntity(
            VALID_GROUP_ID,
            VALID_GROUP_NAME,
            VALID_GROUP_DESCRIPTION
    );

    public final GroupEntity updatedGroupEntity = new GroupEntity(
            VALID_GROUP_ID,
            UPDATE_GROUP_NAME,
            UPDATE_GROUP_DESCRIPTION
    );

    @BeforeEach
    public void setUp() {
        groupService = new GroupServiceImpl(groupRepository);
    }

    @Test
    public void whenGetByValidId_thenReturnGroup() {
        when(groupRepository.findById(VALID_GROUP_ID)).thenReturn(Optional.of(validGroupEntity));

        try {
            testValidGroup(groupService.getGroupById(VALID_GROUP_ID));
        }
        catch (GroupNotFoundException ex) {
            throw new RuntimeException("Unable to retrieve mock group by valid group key.");
        }
    }

    @Test
    public void whenGetByInvalidId_thenThrowNotFoundException() {
        when(groupRepository.findById(INVALID_GROUP_ID)).thenReturn(Optional.empty());
        assertThrows(GroupNotFoundException.class, () -> groupService.getGroupById(INVALID_GROUP_ID));
    }

    @Test
    public void whenCreateValidGroup_thenGroupCreated() {
        when(groupRepository.save(any())).thenReturn(validGroupEntity);
        testValidGroupRequest(groupService.createGroup(validGroup));
    }

    @Test
    public void whenUpdateValidGroup_thenGroupUpdated() {
        when(groupRepository.findById(VALID_GROUP_ID)).thenReturn(Optional.of(validGroupEntity));
        when(groupRepository.save(any())).thenReturn(updatedGroupEntity);

        Group updateGroup = new Group(VALID_GROUP_ID, UPDATE_GROUP_NAME, UPDATE_GROUP_DESCRIPTION);

        Group updatedGroup = groupService.updateGroup(updateGroup);

        assertEquals(VALID_GROUP_ID, updatedGroup.getId());
        assertEquals(UPDATE_GROUP_NAME, updatedGroup.getName());
        assertEquals(UPDATE_GROUP_DESCRIPTION, updatedGroup.getDescription());
    }

    private void testValidGroup(Group returnedGroup) {
        assertEquals(VALID_GROUP_ID, returnedGroup.getId());
        testValidGroupRequest(returnedGroup);
    }

    private void testValidGroupRequest(Group returnedGroup) {
        assertEquals(VALID_GROUP_NAME, returnedGroup.getName());
        assertEquals(VALID_GROUP_DESCRIPTION, returnedGroup.getDescription());
    }

}