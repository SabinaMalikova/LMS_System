import enums.Gender;
import exceptions.MyExceptions;
import models.*;
import serviceImpl.AdminServiceImpl;
import serviceImpl.GroupServiceImpl;
import serviceImpl.LessonServiceImpl;
import serviceImpl.StudentServiceImpl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AdminServiceImpl adminService = new AdminServiceImpl();
        GroupServiceImpl groupService = new GroupServiceImpl();
        LessonServiceImpl lessonService = new LessonServiceImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();

        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));

        if (currentTime.isBefore(LocalTime.of(12, 0))) {
            System.err.println("     Доброе утро!  Сейчас: "+formattedTime);
        } else if (currentTime.isBefore(LocalTime.of(17, 0))) {
            System.err.println("     Добрый день!  Сейчас: "+formattedTime);
        } else if (currentTime.isBefore(LocalTime.of(20, 0))) {
            System.err.println("     Добрый вечер!  Сейчас: "+formattedTime);
        } else {
            System.err.println("     Доброй ночи!  Сейчас: "+formattedTime);
        }

        System.out.println("\n * email: admin@gmail.com,  password: admin123 * \n");

        boolean isLogginTrue = false;
        while (!isLogginTrue) {
            try {
                System.out.println("""
                             выберите одну команду
                        1. войти  (если зарегистрированы)
                        2. изменить пароль  (если забыли пароль)""");
                switch (new Scanner(System.in).nextLine()) {
                    case "1": {
                        Admin admin = new Admin();
                        System.out.println("Введите email: ");
                        admin.setEmail(new Scanner(System.in).nextLine());
                        System.out.println("Введите пароль: ");
                        admin.setPassword(new Scanner(System.in).nextLine());
                        if (adminService.signUp(admin).equals("успешно")){
                            isLogginTrue = true;
                        }break;
                    }
                    case "2": {
                        Admin admin = new Admin();
                        boolean isEmailIsTrue = false;
                        while (!isEmailIsTrue) {
                            System.out.println("Введите ваш email в котром нужно поменять пароль: ");
                            String email = new Scanner(System.in).nextLine();
                            if (email.contains("@")) {
                                admin.setEmail(email);
                                isEmailIsTrue = true;
                            } else {
                                System.out.println("email обязательно должен содержать '@' ");
                            }
                        }
                        boolean isPasswordIsTrue = false;
                        while (!isPasswordIsTrue) {
                            System.out.println("Введите новый пароль: ");
                            String password = new Scanner(System.in).nextLine();
                            if (password.length() >= 8) {
                                admin.setPassword(password);
                                isPasswordIsTrue = true;
                            } else {
                                System.out.println("пароль должен содержать 8 и более значений ");
                            }
                        }
                        System.out.println(adminService.updatePassword(admin));
                        break;
                    }
                    default:
                        throw new MyExceptions("такой опции нет");
                }break;
            } catch (MyExceptions e) {
                System.out.println(e.getMessage());
            }
        }

        boolean istrue = false;
        while (!istrue) {
            try {
                System.out.println("""
                                                                    *** выберите одну команду ***
                                                                            0  ->  exit
                        1  -> add new group                    6  -> update student                         11 -> add new lesson to group
                        2  -> get group by name                7  -> find student by first name             12 -> get lesson by name
                        3  -> update group name                8  -> get all students by group name         13 -> get all lesson by group name
                        4  -> get all groups                   9  -> get all student's lesson               14 -> delete lesson
                        5  -> add new student to group         10 -> delete student                         15 -> delete group""");
                switch (new Scanner(System.in).nextLine()) {
                    case "1": {
                        Group group = new Group();
                        System.out.println("введите название группы: ");
                        group.setGroupName(new Scanner(System.in).nextLine());
                        System.out.println("введите описание: ");
                        group.setDescription(new Scanner(System.in).nextLine());
                        group.setId(GenerateId.genGroupId());
                        System.out.println(groupService.addGroup(group));
                        break;
                    }
                    case "2": {
                        System.out.println("введите название группы которую ищите:");
                        System.out.println(groupService.getGroupByName(new Scanner(System.in).nextLine()));
                        break;
                    }
                    case "3": {
                        Group group = new Group();
                        System.out.println("введите название группы которую нужно обновить: ");
                        String name = new Scanner(System.in).nextLine();
                        System.out.println("введите новое название ");
                        group.setGroupName(new Scanner(System.in).nextLine());
                        System.out.println("введите описание: ");
                        group.setDescription(new Scanner(System.in).nextLine());
                        group.setId(GenerateId.genGroupId());
                        System.out.println(groupService.updateGroupName(name, group));
                        break;
                    }
                    case "4": {
                        System.out.println(groupService.getAllGroups());
                        break;
                    }
                    case "5": {
                        Student student = new Student();
                        System.out.println("введите название группы в которую хотите добавить студента: ");
                        String groupName = new Scanner(System.in).nextLine();
                        System.out.println("введите имя:");
                        student.setName(new Scanner(System.in).nextLine());
                        System.out.println("введите фамилию: ");
                        student.setLastName(new Scanner(System.in).nextLine());
                        System.out.println("введите ваш пол: F или M");
                        boolean istrue2 = false;
                        while (!istrue2){
                            try {
                                student.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                                istrue2 = true;
                            }catch (IllegalArgumentException e){
                                System.out.println("такого знаечния нет, введите снова");
                            }
                        }
                        boolean isEmailTrue = false;
                        while (!isEmailTrue) {
                            System.out.println("Введите email: ");
                            String email = new Scanner(System.in).nextLine();
                            if (email.contains("@")) {
                                student.setEmail(email);
                                isEmailTrue = true;
                            } else {
                                System.out.println("Email обязательно должен содержать '@'. Попробуйте еще раз.");
                            }
                        }
                        boolean isPasswordTrue = false;
                        while (!isPasswordTrue) {
                            System.out.println("Введите пароль: ");
                            String password = new Scanner(System.in).nextLine();
                            if (password.length() >= 8) {
                                student.setPassword(password);
                                isPasswordTrue = true;
                            } else {
                                System.out.println("Пароль должен содержать 8 и более символов. Попробуйте еще раз.");
                            }
                        }
                        System.out.println(studentService.addNewStudentForGroup(groupName, student));
                        break;
                    }
                    case "6": {
                        Student student = new Student();
                        System.out.println("введите email студента которого хотите обновить: ");
                        String email = new Scanner(System.in).nextLine();
                        System.out.println("введите его password: ");
                        String password = new Scanner(System.in).nextLine();
                        System.out.println("введите новое имя: ");
                        student.setName(new Scanner(System.in).nextLine());
                        System.out.println("введите фамилию: ");
                        student.setLastName(new Scanner(System.in).nextLine());
                        System.out.println("введите ваш пол: F или M ");
                        boolean istrue3 = false;
                        while (!istrue3){
                            try {
                                student.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                                istrue3 = true;
                            }catch (IllegalArgumentException e){
                                System.out.println("такого знаечния нет, введите снова");
                            }
                        }
                        boolean isEmailisTrue = false;
                        while (!isEmailisTrue) {
                            System.out.println("введите email: ");
                            String emailNew = new Scanner(System.in).nextLine();
                            if (emailNew.contains("@")) {
                                student.setEmail(emailNew);
                                isEmailisTrue = true;
                            } else {
                                System.out.println("Email обязательно должен содержать '@'. Попробуйте еще раз.");;
                            }
                        }
                        boolean isPasswordIsTrue = false;
                        while (!isPasswordIsTrue) {
                            System.out.println("введите password: ");
                            String passwordNew = new Scanner(System.in).nextLine();
                            if (passwordNew.length() >= 8) {
                                student.setPassword(password);
                                isPasswordIsTrue = true;
                            } else {
                                System.out.println("Пароль должен содержать 8 и более символов. Попробуйте еще раз.");;
                            }
                        }
                        System.out.println(studentService.updateStudent(email, password, student));
                        break;
                    }
                    case "7": {
                        System.out.println("введите имя студента которого ищите:");
                        System.out.println(studentService.findStudentByFirstName(new Scanner(System.in).nextLine()));
                        break;
                    }
                    case "8": {
                        System.out.println("введите название группы из которой хотите посмотреть студентов: ");
                        System.out.println(studentService.getAllStudentsByGroupName(new Scanner(System.in).nextLine()));
                        break;
                    }
                    case "9": {




                        System.out.println("введите имя студента у которой хотите посмотреть lessons");
                        System.out.println(studentService.getAllStudentsLessons(new Scanner(System.in).nextLine()));
                        break;
                    }
                    case "10": {
                        System.out.println("введите email студента которого нужно удалить: ");
                        System.out.println(studentService.deleteStudent(new Scanner(System.in).nextLine()));
                        break;
                    }
                    case "11": {
                        Lesson lesson = new Lesson();
                        System.out.println("введите название группы в которую нужно добавить урок:");
                        String name = new Scanner(System.in).nextLine();
                        System.out.println("введите название урока:");
                        lesson.setLessonName(new Scanner(System.in).nextLine());
                        System.out.println("введите описание урока: ");
                        lesson.setDescription(new Scanner(System.in).nextLine());
                        lesson.setId(GenerateId.genLessonId());
                        System.out.println("ID урока " + lesson.getLessonName() + ": " + lesson.getId());
                        System.out.println(lessonService.addNewLessonToGroup(name, lesson));
                        break;
                    }
                    case "12": {
                        System.out.println("введите название урока которого нужно найти:");
                        System.out.println(lessonService.getLessonByName(new Scanner(System.in).nextLine()));
                        break;
                    }
                    case "13": {
                        System.out.println("введите название группы в которой хотите посмотреть уроки: ");
                        System.out.println(lessonService.getAllLessonsByGroupName(new Scanner(System.in).nextLine()));
                        break;
                    }
                    case "14": {
                        System.out.println("введите название урока который нужно удалить: ");
                        System.out.println(lessonService.deleteLesson(new Scanner(System.in).nextLine()));
                        break;
                    }
                    case "15": {
                        System.out.println("введите название группы, поторую нужно удалить: ");
                        System.out.println(groupService.deleteGroup(new Scanner(System.in).nextLine()));
                        break;
                    }
                    case "0": {
                        System.out.println("хорошего вам дня!");
                        istrue = true;
                        break;
                    }
                    default:
                        throw new MyExceptions("акой опции нет");
                }
            } catch (MyExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }
}