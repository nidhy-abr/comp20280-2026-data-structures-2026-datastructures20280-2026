package project20280.stacksqueues;
import java.util.Stack;


class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        Stack<Character> stack = new Stack<>();
        boolean error = false;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    System.out.println("Error: " + ch + " at index " + i + " does not have an opening bracket");
                    error = true;
                } else {
                    char top = stack.pop();
                    if (!isMatchingPair(top, ch)) {
                        System.out.println("Error: " + ch + " at index " + i + " does not match " + top);
                        error = true;
                    }
                }
            }
        }
        // Check if any opening brackets remain unmatched
        if (!stack.isEmpty()) {
            System.out.println("Error: There are unmatched opening brackets");
            error = true;
        }

        if (!error) {
            System.out.println("The input has balanced brackets");
        }
    }

    private boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '[' && closing == ']') ||
                (opening == '{' && closing == '}');
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}