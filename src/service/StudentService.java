package service;

import models.Lesson;
import models.Student;

import java.util.List;

public interface StudentService {
    String addNewStudentForGroup(String groupName, Student student);                   //ignoreCase
    Student updateStudent(String email,String password, Student student);              // @ обязательно
                                                                                        // обновляем имя, фамилию.
    Student findStudentByFirstName(String studentName);
    List<Student> getAllStudentsByGroupName(String groupName);                          //ignoreCase
    List<Lesson> getAllStudentsLessons(String studentName);
    String deleteStudent(String email);

}
