import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnpackString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        if (isValid(inputString)) {
            StringBuilder resultStringBuilder = new StringBuilder(inputString);
            Pattern pattern = Pattern.compile("(\\d{1})\\[(\\w*)\\]");
            Matcher matcher = pattern.matcher(resultStringBuilder);
            while (matcher.find()){
                resultStringBuilder.replace(matcher.start(), matcher.end(), generateReplacementString(matcher.group(1), matcher.group(2)));
                matcher = pattern.matcher(resultStringBuilder);
            }
            System.out.println(resultStringBuilder.toString());
        } else {
            System.out.println("Входная строка некорректна: возможно отсутствуют необходимые скобки");
        }
    }

    public static String generateReplacementString(String factorString, String value) {
        StringBuilder result = new StringBuilder();
        int factor = Integer.parseInt(factorString);
        for (int i = 0; i < factor; i++){
            result.append(value);
        }
        return result.toString();
    }

    public static boolean isValid(String value){
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < value.length(); i++){
            switch (value.charAt(i)){
                case '[':
                    stack.push(value.charAt(i));
                    break;
                case ']':
                    if (stack.isEmpty()){
                        return false;
                    }
                    if (stack.pop() != '['){
                        return false;
                    }
                    break;
            }
        }
        return (stack.isEmpty()) ? true : false;
    }
}
