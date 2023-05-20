package com.example.utils;

import java.util.regex.Pattern;

public class EmailRegularExpression {
    public static Boolean RegularEmailPattern(String email) {
        String regex="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
       return Pattern.matches(regex, email);//表达式是否满足正则表达式
    }
}
