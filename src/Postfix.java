import java.util.ArrayList;
import java.util.Stack;

public class Postfix {
    private static Stack<Character> stack = new Stack<>();
    private static ArrayList<Character> result = new ArrayList<>();

    public static ArrayList<Character> GetPrefix(String in){
        PostfixNotation(in.toCharArray());
        return result;
    }
    private static void PostfixNotation(char[] infix) {
        int inIndex = 0;
        do {
            char c = infix[inIndex];
            switch (c) {
                case '-':
                    stack.push(c);
                    break;

                case '*':
                    while (stack.size() != 0) {
                        if (stack.peek() == '*' || stack.peek() == '-') {
                            result.add(stack.pop());
                        } else
                            break;
                    }
                    stack.push(c);
                    break;

                case '+':
                    while (stack.size() != 0) {
                        if (stack.peek() == '*' || stack.peek() == '+' || stack.peek() == '-' ) {
                            result.add(stack.pop());
                        } else
                            break;
                    }
                    stack.push(c);
                    break;

                case '(':
                    stack.push(c);
                    break;

                case ')':
                    while (stack.size() != 0) {
                        result.add(stack.pop());
                        if (stack.peek() == '('){
                            stack.pop();
                            if(stack.size()!= 0 && stack.peek() == '-'){
                                result.add(stack.pop());
                            }
                        }
                    }
                    break;

                default:
                    result.add(c);
                    break;
            }
            inIndex++;
        } while (inIndex != infix.length);
        while (stack.size() != 0) {
            result.add(stack.pop());
        }
    }
}
