package service;

import models.Group;

import java.util.List;

public interface GroupService {
    Group addGroup(Group group);
    Group getGroupByName(String name);   //ignoreCase
    Group updateGroupName(String name,Group group);    //ignoreCase
    List<Group> getAllGroups();
    String deleteGroup(String groupName); //ignoreCase

}
