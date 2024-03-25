package serviceImpl;

import dataBase.DataBase;
import exceptions.MyExceptions;
import models.Group;
import models.Lesson;
import models.Student;
import service.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {

    @Override
    public Lesson addNewLessonToGroup(String groupName, Lesson lesson) {
        try {
            for (Group group : DataBase.groups) {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    for (Student student : group.getStudents()) {
                        student.getLessons().add(lesson);
                        System.out.println("успешно добавлено");
                        return lesson;
                    }
                }
            }
            throw new MyExceptions("нет такой группы");
        } catch (MyExceptions e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Lesson getLessonByName(String lessonName) {
        try {
            for (Group group : DataBase.groups) {
                for (Lesson lesson : group.getLessons()) {
                    if (lesson.getLessonName().equalsIgnoreCase(lessonName)) {
                        return lesson;
                    }
                }
            }
            throw new MyExceptions("такого студента нет");
        } catch (MyExceptions e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Lesson> getAllLessonsByGroupName(String groupName) {
        try {
            for (Group group : DataBase.groups) {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    return group.getLessons();
                }
            }
            throw new MyExceptions("такой группы нет");
        } catch (MyExceptions e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteLesson(String lessonName) {
        try {
            for (Group group : DataBase.groups) {
                for (Lesson lesson : group.getLessons()) {
                    if (lesson.getLessonName().equalsIgnoreCase(lessonName)) {
                        group.getLessons().remove(lesson);
                        return "успешно удалено";
                    }
                }
            }
            throw new MyExceptions("такого урока нет");
        } catch (MyExceptions e) {
            return e.getMessage();
        }

    }
}
