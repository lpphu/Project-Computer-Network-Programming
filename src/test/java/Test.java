package src.test.java;
import src.main.java.Model.RailFence;

public class Test {
    public static void main(String[] args) throws Exception {
        RailFence a = new RailFence("https://www.youtube.com/watch?v=fcQiF77bDWg", 2);
        String t1 = a.Encrytion();
        System.out.println(t1);
        a.setWord(t1);
        String t2 = a.Decryption();
        System.out.println(t2);
    }
}
