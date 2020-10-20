import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator {
    public static boolean checkEmail(String email) {
        String allowedCharacters = "A-Za-z0-9!#$%&'*+-/=?^_`{|}~";
        StringBuilder pattern = new StringBuilder();
        pattern.append("^[").append(allowedCharacters).append("]+"); // first part of local part
        pattern.append("(\\.{1}[").append(allowedCharacters).append("]+)*"); // rest of local part
        pattern.append("@{1}"); // only one at-sign
        allowedCharacters = "A-Za-z0-9";
        pattern.append("[").append(allowedCharacters).append("]+"); // first part of domain
        pattern.append("(\\.{1}[").append(allowedCharacters).append("]+)*$"); // rest of domain

        Pattern emailPattern = Pattern.compile(pattern.toString());
        Matcher matcher = emailPattern.matcher(email);

        return matcher.find();
    }

    public static boolean checkPassword(String password) {
        boolean hasDigits = checkOccurrence("0-9", 2, password);
        boolean hasLowerCaseLetters = checkOccurrence("a-z", 2, password);
        boolean hasUpperCaseLetters = checkOccurrence("A-Z", 2, password);
        boolean hasSpecialCharacters = checkOccurrence(
                "\\x21-\\x2F\\x3A-\\x40\\x5B-\\x60\\x7B-\\x7E",
                1,
                password
        );

        return hasDigits && hasLowerCaseLetters && hasUpperCaseLetters && hasSpecialCharacters;
    }

    private static boolean checkOccurrence(String characters, int numberOfOccurrences, String password) {
        StringBuilder searchPattern = new StringBuilder();
        if (numberOfOccurrences > 0) {
            searchPattern.append(".*");
            for (int i = 0; i < numberOfOccurrences; i++) {
                searchPattern.append("[").append(characters).append("]+.*");
            }
        }
        Pattern passwordPattern = Pattern.compile(searchPattern.toString());
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.find();
    }
}
