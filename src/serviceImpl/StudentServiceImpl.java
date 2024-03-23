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
        for (Group group : DataBase.groups) {
            boolean isTrue = false;
            try {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    for (Student student1 : group.getStudents()) {
                        try {
                            if (student1.getEmail().equals(student.getEmail())) {
                                isTrue = true;
                            } else {
                                throw new MyExceptions("такой email уже существует");
                            }
                        } catch (MyExceptions e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (!isTrue) {
                        group.getStudents().add(student);
                        return "успешно добавлено";
                    }
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
    public Student updateStudent(String email, String password, Student student) {
        for (Group group : DataBase.groups) {
            for (int i = 0; i < group.getStudents().size(); i++) {
                Student student1 = group.getStudents().get(i);
                try {
                    if (student1.getEmail().equals(email)) {
                        try {
                            if (student1.getPassword().equals(password)) {
                                group.getStudents().set(i, student);
                                return student;
                            } else {
                                throw new MyExceptions("пороль неверныый");
                            }
                        } catch (MyExceptions e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        throw new MyExceptions("email неверный");
                    }
                } catch (MyExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }


    @Override
    public Student findStudentByFirstName(String studentName) {
        for (Group group : DataBase.groups) {
            for (Student student : group.getStudents()) {
                try {
                    if (student.getName().equalsIgnoreCase(studentName)) {
                        return student;
                    } else {
                        throw new MyExceptions("студент с таким именем не найден");
                    }
                } catch (MyExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public List<Student> getAllStudentsByGroupName(String groupName) {
        for (Group group : DataBase.groups) {
            try {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    return group.getStudents();
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
    public List<Lesson> getAllStudentsLessons(String studentName) {
        for (Group group : DataBase.groups) {
            for (Student student : group.getStudents()) {
                try {
                    if (student.getName().equalsIgnoreCase(studentName)) {



//                        return student.getLessons();



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
    public String deleteStudent(String email) {
        for (Group group : DataBase.groups) {
            for (Student student : group.getStudents()) {
                try {
                    if (student.getEmail().equals(email)) {
                        group.getStudents().remove(student);
                        return "успешно удалено";
                    } else {
                        throw new MyExceptions("студент с таким email не найден");
                    }
                } catch (MyExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }
}
