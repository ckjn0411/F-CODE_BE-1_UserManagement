/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author trinhdtu
 */
public interface Acceptable {

    public final String USERNAME_VALID = "^[^\\s]{5,}$";
    public final String PASSWORD_VALID = "^[^\\s]{6,}$";
    public final String EMAIL_VALID = "^[a-zA-Z0-9._%+-]+@(yahoo|outlook|hotmail|live|msn|gmail)\\.(com|com\\.vn)$";

    public final String NATIONAL_ID_VALID = "^\\d{12}$";
    public final String FULLNAME_VALID = "^[A-Za-z][A-Za-z\\s]{1,24}$";
    public final String GENDER_VALID = "^(male|female)$";
    public final String PHONE_VALID = "^(0|\\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])[0-9]{7}$";
    public final String DESIRED_ROOM_ID_VALID = "^[A-Za-z]\\d{0,4}$";
    public final String POSITIVE_VALID = "^[1-9]\\d*$";
    
    public static boolean isVaLid(String data, String pattern) {
        return data.matches(pattern);
    }

    public final String USERNAME_NOTI = "Username must be at least five characters and contain no spaces.";
    public final String PASSWORD_NOTI = "Password must be at least six characters and contain no spaces.";
    public final String CONFIRM_PW_NOTI = "Confirm password does not match the password.";
    public final String PHONE_NOTI = "Phone number must contain exactly 10 digits.";
    public final String EMAIL_NOTI = "Email format invalid";
    public final String NATIONAL_ID_NOTI = "National ID must be 12 digits.";
    public final String FULLNAME_NOTI = "Full name must be 2-25 characters and start with a letter.";
    public final String GENDER_NOTI = "Gender must be either 'male' or 'female'.";
}
