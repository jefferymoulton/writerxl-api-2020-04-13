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

@Api(value = "/group")
@RestController
@RequestMapping(path = "/group")
@AllArgsConstructor
public class GroupController {

    private GroupService groupService;

    @ApiOperation(value = "Retrieve a group by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Group found."),
            @ApiResponse(code = 404, message = "Group was not found.")
    })
    @GetMapping(path = "/{id}", produces = "application/json")
    public Group getGroupById(@ApiParam("Group Id") @PathVariable int id) {
        try {
            return groupService.getGroupById(id);
        }
        catch (GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find group with id " + id);
        }
    }

    @ApiOperation(value = "Create a new group.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Group created.")
    })
    @ApiImplicitParam(name = "group", value = "Group to be saved", paramType = "body", required = true)
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Group createGroup(@RequestBody @Valid GroupRequest groupRequest) {
        return groupService.createGroup(groupRequest);
    }

    @ApiOperation(value = "Update existing group.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Group saved."),
            @ApiResponse(code = 400, message = "Group with specified ID was not found.")
    })
    @ApiImplicitParam(name = "group", value = "Group to be saved", paramType = "body", required = true)
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
