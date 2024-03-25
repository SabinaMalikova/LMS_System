package serviceImpl;

import dataBase.DataBase;
import exceptions.MyExceptions;
import models.Admin;
import service.AdminService;

import java.util.Arrays;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    List<Admin> admins = Arrays.asList(new Admin("admin@gmail.com", "admin123"));

    @Override
    public String signUp(Admin admin) {
        try {
            for (Admin admin1 : admins) {
                if (admin1.getEmail().equals(admin.getEmail())) {
                    try {
                        if (admin1.getPassword().equals(admin.getPassword())) {
                            return "успешно";
                        }
                        throw new MyExceptions("пароль неверный");
                    } catch (MyExceptions e) {
                        return (e.getMessage());
                    }
                }
            }
            throw new MyExceptions("email неверный");
        } catch (MyExceptions e) {
            return (e.getMessage());
        }
    }


    @Override
    public String updatePassword(Admin admin) {
        try {
            for (Admin admin1 : admins) {
                if (admin1.getEmail().equals(admin.getEmail())) {
                    admin1.setPassword(admin.getPassword());
                    return "пароль успешно изменен";
                }
            }
            throw new MyExceptions("email неверный");
        } catch (MyExceptions e) {
            return e.getMessage();
        }
    }
}
