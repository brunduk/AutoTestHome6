package ru.netology;

import lombok.Value;

import java.util.Random;

public class DataHelper {
    static Random random = new Random();
    private DataHelper(){
    }
    public static VerificationCode getVerificationCode(){
        return new VerificationCode("12345");}

    public static AutoInfo getAutoInfo() {
        return new AutoInfo("vasya", "qwerty123");
    }

    public static CardInfo getCard01() {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardInfo getCard02() {
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static int generateValidAmount(int balance) {
        return new Random().nextInt(Math.abs(balance)) + 1;
    }

    public static int generateInvalidAmount(int balance) {
        return Math.abs(balance) + new Random().nextInt(10000);
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String testId;
    }

    @Value
    public static class AutoInfo {
        String login;
        String password;
    }

}
