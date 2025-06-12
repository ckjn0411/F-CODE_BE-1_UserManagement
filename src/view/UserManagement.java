/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import controllers.Users;
import java.util.List;
import java.util.Scanner;
import models.User;
import utils.Inputter;
import utils.Menu;

/**
 *
 * @author trinhdtu
 */
public class UserManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu("USER MANAGEMENT");

        menu.addNewOption("Create user account.");
        menu.addNewOption("Check exits user.");
        menu.addNewOption("Search user information by name.");
        menu.addNewOption("Update or Delete user information.");
        menu.addNewOption("Save	account	to file.");
        menu.addNewOption("Print list user from	file.");
        menu.addNewOption("Quit!");

        Scanner sc = new Scanner(System.in);
        Users users = new Users();
        users.readFromFile();
        boolean cont = true;
        do {
            menu.print();
            int choice = menu.getChoice();
            switch (choice) {
                case 1:
                    System.out.println("\t________ Create user account. ________");
                    if (users.createUser()) {
                        System.out.println(">>> Create User Successfull!");
                    }
                    break;
                case 2:
                    System.out.println("\t________ Check exits user. ________");
                    String inpUsername = Inputter.getString(
                            "Input the username of the user you want to search: ",
                            "Username not contain number",
                            "[A-Za-z]+$", false);
                    if (users.checkExist(inpUsername)) {
                        System.out.println("Exist user in the database!");
                    } else {
                        System.out.println("No User Found!");
                    }
                    break;
                case 3:
                    System.out.println("\t________ Search user information by name. ________");
                    String nameInput = Inputter.getString(
                            "Input the name or partial name of the user you want to search: ",
                            "Format invalid!",
                            "^[A-Za-z]+$", false);

                    List<User> matched = users.searchUserByName(nameInput);
                    if (!matched.isEmpty()) {
                        for (User user : matched) {
                            System.out.println(user.toString());
                        }
                    } else {
                        System.out.println(">>> Have no any user!");
                    }
                    break;
                case 4:
                    System.out.println("\t________ Update or Delete user information. ________");
                    System.out.println("---- Login account to update and delete ---");
                    User loginUser = users.loginUser();
                    if (loginUser != null) {
                        int choiceFunc4 = Inputter.getInt("4.1: Update user information.\n4.2: Delete user information.\nInput your choice: ",
                                "It must be 1 or 2", 1, 2);
                        if (choiceFunc4 == 1) {
                            if (users.updateUser(loginUser)) {
                                System.out.println(">>> Update successfull!");
                            } else {
                                System.out.println(">>> Update failed!");
                            }

                        } else {

                            if (users.deleteUser(loginUser)) {
                                System.out.println(">>> Delete successfull!");
                            } else {
                                System.out.println(">>> Delete failed!");
                            }
                        }
                    } else {
                        System.out.println("Login failed!");
                    }

                    break;
                case 5:
                    System.out.println("\t________ Save	account	to file. ________");
                    if (users.saveToFile()) {
                        System.out.println(">>> Save account to file successfull!");
                    } else {
                        System.out.println(">>> Save account to file failed");
                    }
                    break;
                case 6:
                    System.out.println("\t________ List User From File. ________");
                    users.displayUsers();
                    break;
                case 7:
                    cont = !menu.confirmYesNo(Inputter.getString(
                            "Do you want quit now? (Y/N): ",
                            "[YyNn]",
                            "Confirm invalid", false));
                    break;
            }

        } while (cont);
    }

}
