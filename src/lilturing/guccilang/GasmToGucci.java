package lilturing.guccilang;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static lilturing.guccilang.Interpret.match;

public class GasmToGucci {
    static void printAsmToGucci() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("program.gasm"));
            StringBuilder sb = new StringBuilder();

            for (String line : lines) {
                line = line.trim(); // allow indentation/formatting in the gasm dialect
                if (match(line, "print_peek", m -> {
                    sb.append("Can't tell me shit\n");
                }));
                else if(match(line, "set_label (\\S+)", m -> {
                    sb.append("I fuck a bitch, her name is ").append(m.group(1)).append("\n");
                }));
                else if(match(line, "print_acc", m -> {
                    sb.append("I can't buy a bitch no wedding ring\n");
                }));
                else if (match(line, "goto (\\S+)( cond (?<cond>\\S+))?", m -> {
                    if (m.group("cond") == null) {
                        sb.append("Rather fuck a bitch named ").append(m.group(1)).append("\n");
                    } else {
                        String mod = "";
                        switch (m.group("cond")) {
                            case "g":
                                mod = "big";
                                break;
                            case "l":
                                mod = "lil";
                                break;
                            case "n":
                                mod = "dumb";
                                break;
                            case "e":
                                mod = "bad";
                                break;
                        }
                        sb.append("Rather fuck a ").append(mod).append(" bitch named ").append(m.group(1)).append("\n");
                    }
                }));
                else if (match(line, "push (?<num>(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?)$", m -> {
                    sb.append(m.group("num")).append(" on my wrist\n");
                }));
                else if (match(line, "pop$", m -> {
                    sb.append("I just broke my wrist\n");
                }));
                else if (match(line, "cmp", m -> {
                    sb.append("Yuh\n");
                }));
                else if (match(line, "pop_to_acc", m -> {
                    sb.append("Esskeetit\n");
                }));
                else if (match(line, "dup", m -> {
                    sb.append("None of this shit be new to me\n");
                }));
                else if (match(line, "inc", m -> {
                    sb.append("Gucci gang\n");
                }));
                else if (match(line, "dec", m -> {
                    sb.append("Brrr\n");
                }));
                else if (match(line, "add", m -> {
                    sb.append("I can move these bricks\n");
                }));
                else if (match(line, "sub$", m -> {
                    sb.append("Smokepurpp\n");
                }));
                else if (match(line, "mul", m -> {
                    sb.append("My lean cost more than your rent\n");
                }));
                else if (match(line, "div", m -> {
                    sb.append("Me and my grandma take meds\n");
                }));
                else if (match(line, "mod", m -> {
                    sb.append("I been had commando\n");
                }));
                else if (match(line, "push_acc", m -> {
                    sb.append("Only wear designer\n");
                }));
                else if (match(line, "pop_to_ext", m -> {
                    sb.append("Walk in the trap like a boss\n");
                }));
                else if (match(line, "push_ext", m -> {
                    sb.append("I came in with the sauce\n");
                }));
                else if (match(line, "call (\\S+)", m -> {
                    sb.append(m.group(1)).append(" love do cocaine\n");
                }));
                else if (match(line, "subroutine (\\S+)", m -> {
                    sb.append("Told ").append(m.group(1)).append(" I don't do confessions\n");
                }));
                else if (match(line, "return", m -> {
                    sb.append("Ooh\n");
                }));
                else if (match(line, "#(.*)", m -> {
                    sb.append("( ").append(m.group(1)).append(" )\n");
                }));
            }

            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
