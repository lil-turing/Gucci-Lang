package lilturing.guccilang;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("gasm")) GasmToGucci.printAsmToGucci();
        else Interpret.run();
    }
}
