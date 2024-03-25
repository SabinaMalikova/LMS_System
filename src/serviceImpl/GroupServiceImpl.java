package serviceImpl;

import dataBase.DataBase;
import exceptions.MyExceptions;
import models.Group;
import service.GroupService;

import java.util.List;

public class GroupServiceImpl implements GroupService {
    @Override
    public Group addGroup(Group group) {
        boolean isTrue = false;
        try {
            for (Group group1 : DataBase.groups) {
                if (group1.getGroupName().equalsIgnoreCase(group.getGroupName())) {
                    isTrue = true;
                }
            }
            if (!isTrue) {
                DataBase.groups.add(group);
                System.out.println("успешно добавлена");
                return group;
            }
            throw new MyExceptions("такая группа уже существует!");
        } catch (MyExceptions e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Group getGroupByName(String name) {
        try {
            for (Group group : DataBase.groups) {
                if (group.getGroupName().equalsIgnoreCase(name)) {
                    return group;
                }
            }
            throw new MyExceptions("нет такой группы");
        } catch (MyExceptions e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Group updateGroupName(String name, Group group) {
        try {
            for (int i = 0; i < DataBase.groups.size(); i++) {
                Group group1 = DataBase.groups.get(i);
                if (group1.getGroupName().equalsIgnoreCase(name)) {
                    DataBase.groups.set(i, group);
                    return group;
                }
            }
            throw new MyExceptions("нет такой группы");
        } catch (MyExceptions e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Group> getAllGroups() {
        return DataBase.groups;
    }

    @Override
    public String deleteGroup(String groupName) {
        try {
            for (Group group : DataBase.groups) {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    DataBase.groups.remove(group);
                    return "успешно удалено";
                }
            }
            throw new MyExceptions("нет такой группы");
        } catch (MyExceptions e) {
            return e.getMessage();
        }
    }
}
