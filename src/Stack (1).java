public class Stack { // arrays can't use Generics, so using Object instead. ArrayList CAN use generics, however (important for your lab).
//    attributes/instance variables
    public static final int MAX_CAPACITY = 100;
    private Object[] data;
    private int top = -1;

//    constructors
    public Stack() {
        data = new Object[MAX_CAPACITY];
    }
    public Stack(Object[] data) {
        this.data = data;
    }

//  methods - creating methods from the Java Stack class (push, pop, peek, isEmpty)
    public boolean isEmpty() {
        return top == -1;
    }

    public Object peek() throws StackEmptyException{
        if(isEmpty()) {
            throw new StackEmptyException();
        }
        return data[top];
    }

    public void push(Object o) throws StackFullException{
        if(top == MAX_CAPACITY - 1) {
            throw new StackFullException();
        }
        top++;
        data[top] = o;
    }

    public Object pop() throws StackEmptyException {
        if(isEmpty()) {
            throw new StackEmptyException();
        }

        top--;
        return data[top+1];
    }

//    toString
    public String toString() {
        if(isEmpty()) return "Stack Empty!";

        String stack = "";
        for(int i = 0; i<=top; i++) {
            stack+=data[i] + " ";
        }
        return stack;
    }
}

//our exceptions
class StackFullException extends Exception {
    public StackFullException() {
        System.err.println("Stack is full. Can't push onto stack!");
    }
}

class StackEmptyException extends Exception {
    public StackEmptyException() {
        System.err.println("Stack is empty!");
    }
}
