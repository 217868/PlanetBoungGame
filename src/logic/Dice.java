package logic;

public class Dice {

    public int throwd6(){
        return (int) (Math.random() * 6 + 1);
    }

    public int throwd6twice(){
        return (int)(Math.random() * 12 + 1);
    }
}
