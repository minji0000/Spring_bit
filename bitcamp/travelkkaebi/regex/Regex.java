package com.bitcamp.travelkkaebi.regex;

public class Regex {
    /**
     * username 정규식 -> 시작은 영문+숫자 조합에, 첫 글자 영문, 6~16자리
     * password 정규식 -> 알파벳+숫자+특수문자 중 2개 이상 조합해서 8-16자리
     * email 정규식 -> ***@****.***
     * phone 정규식 -> 010-1234-5678
     */
    public final static String USERNAME = "^[a-zA-Z0-9]{6,16}$";
    public final static String PASSWORD = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$";
    public final static String EMAIL = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
    public final static String PHONE = "^\\d{3}\\d{4}\\d{4}$";
}
