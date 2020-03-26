package com.writerxl.api.service;

import com.writerxl.api.model.Group;

public interface GroupService {

    Group getGroupById(int id);

    Group createGroup(Group group);

    Group updateGroup(Group group);

}
