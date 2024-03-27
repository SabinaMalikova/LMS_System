package serviceImpl;

import dataBase.DataBase;
import exceptions.MyExceptions;
import models.Group;
import models.Lesson;
import models.Student;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public String addNewStudentForGroup(String groupName, Student student) {
        try {
            for (Group group : DataBase.groups) {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    boolean isEmailTrue = false;
                    for (Student existingStudent : group.getStudents()) {
                        if (existingStudent.getEmail().equals(student.getEmail())) {
                            isEmailTrue = true;
                            break;
                        }
                    }
                    if (isEmailTrue) {
                        throw new MyExceptions("Такой email уже существует в данной группе");
                    }
                    group.getStudents().add(student);
                    return "Студент успешно добавлен в группу";
                }
            }
            throw new MyExceptions("Группа с указанным названием не найдена");
        } catch (MyExceptions e) {
            return e.getMessage();
        }
    }

    @Override
    public Student updateStudent(String email, String password, Student student) {
        try {
            for (Group group : DataBase.groups) {
                for (int i = 0; i < group.getStudents().size(); i++) {
                    Student student1 = group.getStudents().get(i);
                    if (student1.getEmail().equals(email)) {
                        try {
                            if (student1.getPassword().equals(password)) {
                                group.getStudents().set(i, student);
                                return student;
                            }
                            throw new MyExceptions("пороль неверныый");
                        } catch (MyExceptions e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            throw new MyExceptions("email неверный");
        } catch (MyExceptions e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public Student findStudentByFirstName(String studentName) {
        try {
            for (Group group : DataBase.groups) {
                for (Student student : group.getStudents()) {
                    if (student.getName().equalsIgnoreCase(studentName)) {
                        return student;
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
    public List<Student> getAllStudentsByGroupName(String groupName) {
        try {
            for (Group group : DataBase.groups) {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    return group.getStudents();
                }
            }
            throw new MyExceptions("такой группы нет");
        } catch (MyExceptions e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Lesson> getAllStudentsLessons(String studentName) {
        try {
            for (Group group : DataBase.groups) {
                for (Student student : group.getStudents()) {
                    if (student.getName().equalsIgnoreCase(studentName)) {
                        return student.getLessons();
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
    public String deleteStudent(String email) {
        try {
            for (Group group : DataBase.groups) {
                for (Student student : group.getStudents()) {
                    if (student.getEmail().equals(email)) {
                        group.getStudents().remove(student);
                        return "успешно удалено";
                    }
                }
            }
            throw new MyExceptions("студент с таким email не найден");
        } catch (MyExceptions e) {
            return e.getMessage();
        }
    }
}
