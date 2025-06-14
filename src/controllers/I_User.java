/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controllers;

import java.util.List;
import models.User;

/**
 *
 * @author trinhdtu
 */
public interface I_User{
    boolean createUser();
    boolean checkExist(String username);
    List<User> searchUserByName(String name);
    boolean updateUser(User user);
    boolean deleteUser(User user);
    String passwordEncryp(String password);
    void displayUsers();
    void sortByFirstName(List<User> u);
    boolean saveToFile();
    boolean readFromFile();
    User loginUser();
}
