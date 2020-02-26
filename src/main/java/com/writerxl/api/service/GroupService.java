package com.writerxl.api.service;

import com.writerxl.api.exception.GroupNotFoundException;
import com.writerxl.api.model.Group;
import com.writerxl.api.model.request.GroupRequest;

public interface GroupService {

    Group getGroupById(int id) throws GroupNotFoundException;

    Group createGroup(GroupRequest groupRequest);

    Group updateGroup(Group group) throws GroupNotFoundException;

}
