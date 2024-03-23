package service;

import models.Lesson;

import java.util.List;

public interface LessonService {
    Lesson addNewLessonToGroup(String groupName, Lesson lesson);         //ignoreCase
    Lesson getLessonByName(String lessonName);
    List<Lesson> getAllLessonsByGroupName(String groupName);             //ignoreCase
    String deleteLesson(String lessonName);                              //ignoreCase



}
