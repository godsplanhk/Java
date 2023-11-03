import java.util.Stack;

public class Test<T extends Comparable<T>>{
    private Stack<T> stack;

    public Test() {
        stack = new Stack<>();
    }

    public void push(T data) {
        Stack<T> tempStack = new Stack<>();

        while (!stack.isEmpty() && data.compareTo(stack.peek()) > 0) {
            tempStack.push(stack.pop());
        }

        stack.push(data);

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    public T pop() {
        if (!stack.isEmpty()) {
            return stack.pop();
        } else {
            return null;
        }
    }

    public T peek() {
        if (!stack.isEmpty()) {
            return stack.peek();
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        SortedStack<Integer> intStack = new Test<>();
        intStack.push(3);
        intStack.push(1);
        intStack.push(5);
        intStack.push(2);

        while (!intStack.isEmpty()) {
            System.out.println(intStack.pop());
        }

        SortedStack<String> stringStack = new Test<>();
        stringStack.push("banana");
        stringStack.push("apple");
        stringStack.push("cherry");

        while (!stringStack.isEmpty()) {
            System.out.println(stringStack.pop());
        }

        SortedStack<Double> doubleStack = new Test<>();
        doubleStack.push(3.14);
        doubleStack.push(2.71);

        while (!doubleStack.isEmpty()) {
            System.out.println(doubleStack.pop());
        }
    }
}
