package serviceImpl;

import dataBase.DataBase;
import exceptions.MyExceptions;
import models.Group;
import models.Lesson;
import service.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {

    @Override
    public Lesson addNewLessonToGroup(String groupName, Lesson lesson) {
        for (Group group : DataBase.groups) {
            try {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    group.getLessons().add(lesson);
                    System.out.println("успешно добавлено");
                    return lesson;
                } else {
                    throw new MyExceptions("нет такой группы");
                }
            } catch (MyExceptions e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Lesson getLessonByName(String lessonName) {
        for (Group group : DataBase.groups) {
            for (Lesson lesson : group.getLessons()) {
                try {
                    if (lesson.getLessonName().equalsIgnoreCase(lessonName)) {
                        return lesson;
                    } else {
                        throw new MyExceptions("такого студента нет");
                    }
                } catch (MyExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public List<Lesson> getAllLessonsByGroupName(String groupName) {
        for (Group group : DataBase.groups) {
            try {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    return group.getLessons();
                } else {
                    throw new MyExceptions("такой группы нет");
                }
            } catch (MyExceptions e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String deleteLesson(String lessonName) {
        for (Group group : DataBase.groups) {
            for (Lesson lesson : group.getLessons()) {

                if (lesson.getLessonName().equalsIgnoreCase(lessonName)) {
                    group.getLessons().remove(lesson);
                    return "успешно удалено";
                } else {
                    return ("такого урока нет");
                }
            }
        }
        return null;
    }
}
