package com.writerxl.api.controller;

import com.writerxl.api.exception.GroupNotFoundException;
import com.writerxl.api.model.Group;
import com.writerxl.api.model.request.GroupRequest;
import com.writerxl.api.service.GroupService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/group")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public Group getGroupById(@ApiParam("Group Id") @PathVariable int id) {
        try {
            return groupService.getGroupById(id);
        }
        catch (GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find group with id " + id);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Group createGroup(@RequestBody @Valid GroupRequest groupRequest) {
        return groupService.createGroup(groupRequest);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Group updateGroup(@RequestBody @Valid Group group) {
        try {
            return groupService.updateGroup(group);
        }
        catch (GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id " + group.getId() + " not found");
        }
    }

}
