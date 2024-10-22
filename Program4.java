/*
Zava
CS2050
 */


import java.io.*;

public class Program4 {
        public static void main(String[] args) {
            try {
                String line;
                BufferedReader br = new BufferedReader(new FileReader("Program4.txt"));
                BufferedWriter bw = new BufferedWriter(new FileWriter("Program4.out"));

                bw.write("Zava \n Array Stack \n");
                while ((line = br.readLine()) != null) {
                    String infix = line.trim();
                    String postfix = InfixToPostfix(infix);
                    bw.write(infix + "\t->\t" + postfix + "\n");
                }

                br.close();

                BufferedReader read = new BufferedReader(new FileReader("Program4.txt"));


                bw.write("Linked Stack \n");
                while ((line = read.readLine()) != null) {
                    String infix = line.trim();
                    String postfix = InfixToPostfixLinkedStack(infix);
                    bw.write(infix + "\t->\t" + postfix + "\n");
                }

                read.close();
                bw.close();
            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Unable to read/write/ find the files");
            }

        }

    public static boolean isOperand(char a){
        switch (a) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '0':
            case '.':
                 return true;

        }
        return false;
    }

    public static int operatorOrder(char a){
            switch(a) {
                case '*':
                case '/':
                case '%':
                    return 2;
                case '+':
                case '-':
                    return 1;
            }
            return 0;
    }

        public static String InfixToPostfix(String s) {
            int counter = 0;
            ArrayStack stack = new ArrayStack(32);
            StringBuilder postfix = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {

                char current = s.charAt(i);
                if (current == ' ') {
                    postfix.append(" ");
                }
                else if(Character.isDigit(current) || current == '.'){
                    postfix.append(current);

                }
                else if (current == '(') {
                    stack.push(current);
                    counter++;
                }
                else if (current == ')') {
                    counter--;
                    while(!stack.empty() && stack.peek() != '(') {
                        postfix.append(" " + stack.pop() + " ");
                    }
                    stack.pop();

                }
                else if (operatorOrder(current) == 1 || operatorOrder(current) == 2){
                    while (!stack.empty() && operatorOrder(stack.peek()) >= operatorOrder(current)) {
                        postfix.append(" " + stack.pop() + " ");
                    }
                    stack.push(current);
                }
                else if (!isOperand(current) && operatorOrder(current) == 0){
                    return (s + " invalid character");
                }
            }
            while (!stack.empty()) {
                postfix.append(" "+ stack.pop() + " ");
            }
            if (counter != 0){
                return (s + " unmatched parens");
            }
            return postfix.toString();
        }

    public static String InfixToPostfixLinkedStack(String s) {
        int counter = 0;
        LinkedStack stack = new LinkedStack();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char current = s.charAt(i);
            if (current == ' ') {
                postfix.append(" ");
            }
            else if(Character.isDigit(current) || current == '.'){
                postfix.append(current);

            }
            else if (current == '(') {
                stack.push(current);
                counter++;
            }
            else if (current == ')') {
                counter--;
                while(!stack.empty() && stack.peek() != '(') {
                    postfix.append(" " + stack.pop() + " ");
                }
                stack.pop();

            }
            else if (operatorOrder(current) != 0){
                while (!stack.empty() && operatorOrder(stack.peek()) >= operatorOrder(current)) {
                    postfix.append(" " + stack.pop() + " ");
                }
                stack.push(current);
            }
            else if (!isOperand(current) && operatorOrder(current) == 0){
                return (s + " invalid character");
            }
        }
        while (!stack.empty()) {
            postfix.append(" "+ stack.pop() + " ");
        }
        if (counter != 0){
            return (s + " unmatched parens");
        }
        return postfix.toString();
    }
}
