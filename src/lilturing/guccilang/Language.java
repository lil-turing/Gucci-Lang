package lilturing.guccilang;

import java.util.Stack;

class Language {
    private static Stack<Frame> callStack = new Stack<>();
    private static Stack<Double> valueStack = new Stack<>();
    private static double cmpFlag = 0;

    static class Frame {
        Frame(int call) { this.call = call; }
        double acc = 0,
               ext = 0;
        final int call;
    }

    private static Frame frame() {
        return callStack.peek();
    }

    static void enterFrame(int call) {
        callStack.push(new Frame(call));
    }

    static int exitFrame() {
        return callStack.pop().call;
    }

    static double increment() {
        return ++frame().acc;
    }

    static double decrement() {
        return --frame().acc;
    }

    static double compare(double a, double b) {
        cmpFlag = a - b;
        return cmpFlag;
    }

    static double getCmpFlag() {
        return cmpFlag;
    }

    static double getAcc() {
        return frame().acc;
    }

    private static void setAcc(double a) {
        frame().acc = a;
    }

    static void push(double a) {
        valueStack.push(a);
    }

    static void popToAcc() {
        frame().acc = pop();
    }

    static void pushAcc() {
        push(frame().acc);
    }

    static void popToExt() {
        frame().ext = pop();
    }

    static void pushExt() {
        push(frame().ext);
    }

    static void dupe() {
        push(peek());
    }

    static void add() {
        frame().acc += pop();
    }

    static void sub() {
        frame().acc -= pop();
    }

    static void mul() {
        frame().acc *= pop();
    }

    static void div() {
        frame().acc /= pop();
    }

    static void mod() {
        frame().acc %= pop();
    }

    static double peek() {
        return valueStack.peek();
    }

    static double pop() {
        return valueStack.pop();
    }
}
