package db;

public class TestConnection {

    public static void main(String[] args) {

        boolean result = Login.loginUser("test@gmail.com", "1234");

        if (result) {
            System.out.println("👉 LOGIN SUCCESS");
        } else {
            System.out.println("👉 INVALID EMAIL OR PASSWORD");
        }
    }
}