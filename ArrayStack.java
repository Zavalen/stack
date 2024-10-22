/*
Zava
CS2050
03/10/2024
 */

public class ArrayStack {
    int max;
    char[] stackArray;
    int top;

    public ArrayStack(int N) {

        this.max = N;
        this.stackArray = new char[N];
        this.top = -1;
    }

    public void push(char a) {

        if (full()) {
            System.out.println("Stack overflow");

        }

        stackArray[++top] = a;
    }

    public char pop() {

        if (empty()) {
            throw new java.util.EmptyStackException();

        }
        return stackArray[top--];
    }

    public char peek() {


        if (empty()) {
            throw new java.util.EmptyStackException();

        }
        return stackArray[top];
    }

    public boolean empty() {

        return (top == -1);
    }

    public boolean full() {

        return (top ==  max - 1);
    }

    public int size() {

        return (top + 1);
    }
}
