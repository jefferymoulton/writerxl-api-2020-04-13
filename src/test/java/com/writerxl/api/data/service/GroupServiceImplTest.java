package com.writerxl.api.data.service;

import com.writerxl.api.data.model.GroupEntity;
import com.writerxl.api.data.repository.GroupRepository;
import com.writerxl.api.exception.GroupNotFoundException;
import com.writerxl.api.model.Group;
import com.writerxl.api.model.request.GroupRequest;
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

    private static final int INVALID_GROUP_ID = 98765;

    private final GroupRequest validGroupRequest = new GroupRequest(
            VALID_GROUP_NAME,
            VALID_GROUP_DESCRIPTION
    );

    private final GroupEntity validGroupEntity = new GroupEntity(
            VALID_GROUP_ID,
            VALID_GROUP_NAME,
            VALID_GROUP_DESCRIPTION
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
        testValidGroupRequest(groupService.createGroup(validGroupRequest));
    }

    // TODO: Add test for update group.

    private void testValidGroup(Group returnedGroup) {
        assertEquals(VALID_GROUP_ID, returnedGroup.getId());
        testValidGroupRequest(returnedGroup);
    }

    private void testValidGroupRequest(Group returnedGroup) {
        assertEquals(VALID_GROUP_NAME, returnedGroup.getName());
        assertEquals(VALID_GROUP_DESCRIPTION, returnedGroup.getDescription());
    }

}