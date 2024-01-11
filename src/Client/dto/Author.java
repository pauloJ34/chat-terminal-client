package Client.dto;

import java.util.Random;

import Client.printCustom.Colors;

public class Author {
    private Random gerador = new Random();

    private String nickname;
    private String color = Colors.values()[gerador.nextInt(7) + 7].toString();
    private String message = null;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return nickname + "Ξ" + message + "Ξ" + color;
    }

}
