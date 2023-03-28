public class Main {
    public static void main(String[] args) {
        // sign in
        SignIn formSignIn = new SignIn();
        SignUp formSignUp = new SignUp();
        ChatRoom room = new ChatRoom("Server");
        formSignIn.setVisible(true);
    }
}
