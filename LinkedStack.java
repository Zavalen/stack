/*
Zava
CS2050
 */

import java.util.EmptyStackException;
class Node {
    char data;
    Node next;

    public Node(char data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedStack {
    private Node top;

    public LinkedStack() {
        this.top = null;
    }

    public void push(char c) {
        Node newNode = new Node(c);
        newNode.next = top;
        top = newNode;
    }

    public char pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        char data = top.data;
        top = top.next;
        return data;
    }

    public char peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    public boolean empty() {
        return (top == null);
    }
}