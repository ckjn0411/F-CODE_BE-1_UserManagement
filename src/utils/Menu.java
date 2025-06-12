/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;
import utils.Inputter;

/**
 *
 * @author trinhdtu
 */
public class Menu {

    public ArrayList<String> optionList = new ArrayList<>();
    public String title;

    public Menu(String title) {
        this.title = title;
    }

    public void addNewOption(String newOpion) {
        optionList.add(newOpion);
    }

    public void print() {
        int count = 1;
        System.out.println("------------------------------------------------------");
        System.out.println("\t__________" + title + "__________");
        for (String op : optionList) {
            System.out.println(count + "." + op);
            count++;
        }
        System.out.println("------------------------------------------------------");

    }

    public int getChoice() {
        int choice = Inputter.getInt("Input your choice: ",
                "Your choice must be between 1 and " + optionList.size(),
                1, optionList.size());
        return choice;
    }

    public boolean confirmYesNo(String confirm) {
        return "Y".equalsIgnoreCase(confirm);
    }
}
