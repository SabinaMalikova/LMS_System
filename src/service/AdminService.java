package service;

import models.Admin;

public interface AdminService {


    String signUp(Admin admin);
    String updatePassword(Admin admin);

}
