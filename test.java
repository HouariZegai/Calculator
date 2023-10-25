public class BadNullPointerExceptionHandling {
    public static void main(String[] args) {
        String userInput = null;
        try {
            int length = userInput.length();
            System.out.println("Length of input: " + length);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught: " + e.getMessage());
        }
    }
}
