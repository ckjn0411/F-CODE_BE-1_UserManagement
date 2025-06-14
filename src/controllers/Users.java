/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import models.User;
import utils.Acceptable;
import utils.Inputter;

/**
 *
 * @author trinhdtu
 */
public class Users extends ArrayList<User> implements I_User {

    private static final long serialVersionUID = 3476268343297655213L;
    private String pathFile = "users.dat";

    public Users() {
    }

    @Override
    public boolean createUser() {
        String username;
        while (true) {
            username = Inputter.getString("Input username: ", Acceptable.USERNAME_NOTI, Acceptable.USERNAME_VALID, false);
            if (!checkExist(username)) {
                break;
            } else {
                System.out.println("Username exist. Please input username again!");
            }
        }

        String firstName = Inputter.getString("Input first name: ", "Name cannot contain numeric characters", "[a-zA-Z]*", false);
        String lastName = Inputter.getString("Input last name: ", "Name cannot contain numeric characters", "[a-zA-Z]*", false);
        String password = Inputter.getString("Input password: ", Acceptable.PASSWORD_NOTI, Acceptable.PASSWORD_VALID, false);
        String confirmPw = Inputter.getString("Confirm your password: ", Acceptable.CONFIRM_PW_NOTI, password, false);
        String email = Inputter.getString("Input email: ", Acceptable.EMAIL_NOTI, Acceptable.EMAIL_VALID, false);
        String phone = Inputter.getString("Input your phone: ", Acceptable.PHONE_NOTI, Acceptable.PHONE_VALID, true);

        String encryptPassword = passwordEncryp(password);
        User nUser = new User(username, firstName, lastName, email, phone, encryptPassword, encryptPassword);
        this.add(nUser);
        return true;
    }

    @Override
    public boolean checkExist(String username) {
        ArrayList<User> userList = readFromFileToNewList();
        int index = userList.indexOf(new User(username));
        System.out.println(index);
        return index != -1 ? true : false;
    }

    @Override
    public List<User> searchUserByName(String name) {
        ArrayList<User> userList = readFromFileToNewList();
        List<User> result = new ArrayList<>();
        for (User user : userList) {
            if (user.getFullname().toLowerCase().contains(name.toLowerCase())) {
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public boolean updateUser(User user) {
        boolean updated = true;
        String firstName = Inputter.getString("Input first name (Enter to skip): ", "Name cannot contain numeric characters", "[a-zA-Z]*", true);
        if (!firstName.isEmpty()) {
            user.setFirstName(firstName);
        }
        String lastName = Inputter.getString("Input last name (Enter to skip): ", "Name cannot contain numeric characters", "[a-zA-Z]*", true);
        if (!lastName.isEmpty()) {
            user.setLastName(lastName);
        }
        String password = Inputter.getString("Input password (Enter to skip): ", Acceptable.PASSWORD_NOTI, Acceptable.PASSWORD_VALID, true);
        if (!password.isEmpty()) {
            String confirmPw = Inputter.getString("Confirm your password: ", Acceptable.CONFIRM_PW_NOTI, password, true);
            user.setPassword(passwordEncryp(password));
        }

        String email = Inputter.getString("Input email (Enter to skip): ", Acceptable.EMAIL_NOTI, Acceptable.EMAIL_VALID, true);
        if (!email.isEmpty()) {
            user.setEmail(email);
        }
        String phone = Inputter.getString("Input your phone (Enter to skip): ", Acceptable.PHONE_NOTI, Acceptable.PHONE_VALID, true);
        if (!phone.isEmpty()) {
            user.setPhone(phone);
        }
        this.saveToFile();
        return updated;
    }

    @Override
    public boolean deleteUser(User userDel) {
        boolean deleted = false;
        Iterator<User> iterator = this.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equals(userDel.getUsername())) {
                iterator.remove();
                deleted = true;
            }
        }
        saveToFile();
        return deleted;
    }

    @Override
    public String passwordEncryp(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }

    @Override
    public boolean saveToFile() {
        boolean isSaved = false;
        if (pathFile.isEmpty()) {
            System.out.println("Error: pathFile is null");
            return isSaved;
        }
        File file = new File(pathFile);
        try ( FileOutputStream f = new FileOutputStream(file);  ObjectOutputStream oos = new ObjectOutputStream(f)) {
            oos.writeObject(this);
            isSaved = true;
        } catch (Exception e) {
            System.out.println("Save file in Customers error: " + e);

        }
        return isSaved;
    }

    @Override
    public boolean readFromFile() {
        boolean isRead = false;
        if (pathFile == null) {
            System.out.println("Error: pathFile is null");
            return isRead;
        }
        File file = new File(pathFile);

        if (file.length() > 0) {
            try {
                FileInputStream fos = new FileInputStream(file);
                ObjectInputStream oos = new ObjectInputStream(fos);

                ArrayList<User> loadCustomers = (ArrayList<User>) oos.readObject();
                this.clear();
                this.addAll(loadCustomers);
                isRead = true;
                fos.close();
                oos.close();
            } catch (Exception e) {
                System.out.println("Customers file error" + e);
            }
        }

        return isRead;
    }

    public ArrayList<User> readFromFileToNewList() {
        ArrayList<User> tempList = new ArrayList<>();
        File file = new File(pathFile);
        if (file.length() == 0) {
            return tempList;
        }

        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            tempList = (ArrayList<User>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error reading temp customers: " + e);
        }
        return tempList;
    }

    @Override
    public void displayUsers() {
        ArrayList<User> userList = readFromFileToNewList();
        sortByFirstName(userList);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Override
    public void sortByFirstName(List<User> u) {
        Collections.sort(u, (User u1, User u2)
                -> u1.getFirstName().compareToIgnoreCase(u2.getFirstName()));
    }

    @Override
    public User loginUser() {
        ArrayList<User> userList = readFromFileToNewList();
        String username = Inputter.getString("Username: ", Acceptable.USERNAME_NOTI, Acceptable.USERNAME_VALID, false);

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                String password = Inputter.getString("Password: ", "Wrong password!", Acceptable.PASSWORD_VALID, false);
                String encryptPassword = passwordEncryp(password);
                if (user.getPassword().equals(encryptPassword)) {
                    return user;
                } else {
                    System.out.println("Wrong password!");
                }

            }
        }
        System.out.println("Username does not exist!");

        return null;

    }

}
