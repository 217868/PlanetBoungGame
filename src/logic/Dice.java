package logic;

public class Dice {

    public static int throwd6(){
        return (int) (Math.random() * 6 + 1);
    }

    public static int throwd6twice(){
        return (int)(Math.random() * 12 + 1);
    }

    public static int throwd3(){
        return (int)(Math.random() * 3 + 1);
    }
}
