/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author trinhdtu
 */
public class Inputter implements Acceptable {

    public static Scanner sc = new Scanner(System.in);

    public static String getString(String inpMsg, String errMsg, String pattern, boolean allowEmpty) {
        System.out.print(inpMsg);
        while (true) {
            try {
                String str = sc.nextLine().trim();
                if (str.isEmpty()) {
                    if (allowEmpty) {
                        return "";
                    } else {
                        System.out.println("That field is required!");
                    }
                }

                if (!Acceptable.isVaLid(str, pattern)) {
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    public static int getInt(String inpMsg, String errMsg, int min, int max) {
        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }

        System.out.print(inpMsg);
        while (true) {
            try {
                String input = sc.nextLine().trim();
                int num = -1;
                if (input.isEmpty()) {
                    System.out.println("That field is requied!");
                } else {
                    num = Integer.parseInt(input);
                    if (num < min || num > max) {
                        throw new Exception();
                    }

                }

                return num;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

}
