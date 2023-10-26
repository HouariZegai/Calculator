public class App {
     static void stringEqualityCheckNoncompliant(String string1, String string2) {
    // Noncompliant: the == operator doesn't compare the contents of the strings.
    if(string1 == string2) {
        System.out.println("The strings are equal.");
        }
    }

    public static void main(String[] args) {
        stringEqualityCheckNoncompliant("dfhdh","dfhdh");
    }

}