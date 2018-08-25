package lilturing.guccilang;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lilturing.guccilang.Language.*;

public class Interpret {

    private static List<String> lines = new ArrayList<>();
    private static HashMap<String, Integer> labels = new HashMap<>();
    private static HashMap<String, Integer> subroutines = new HashMap<>();
    private static final int[] i = {1};
    private static final DecimalFormat df = new DecimalFormat("0.###");

    static void run() {
        try {
            lines.add("");
            lines.addAll(Files.readAllLines(Paths.get("program.gucci")));

            //first check for labels and subroutines

            for (int i = 1; i < lines.size(); i++) {
                int finalI = i;
                match(lines.get(i), "I fuck a bitch, her name is (\\S+)", m -> {
                    labels.put(m.group(1), finalI);
                });
                match(lines.get(i), "(\\S+) love do cocaine", m -> {
                    subroutines.put(m.group(1), finalI);
                });
            }

            //now walk program

            initFrame();
            final boolean[] skipSub = {false};

            for (; i[0] < lines.size(); i[0]++) {
                String line = lines.get(i[0]);
                if (line.charAt(0) == '(') continue;
                if (skipSub[0]) {
                    match(line, "Ooh|Huh", m -> {
                        skipSub[0] = false;
                    });
                    continue;
                }
                if (match(line, "Can't tell me shit", m -> {
                    System.out.println(df.format(peek()));
                }));
                else if (match(line, "I can't buy a bitch no wedding ring", m -> {
                    System.out.println(df.format(getAcc()));
                }));
                else if (match(line, "Rather fuck a( (\\S+))? bitch named (\\S+)", m -> {
                    if (m.groupCount() < 2) {
                        i[0] = labels.getOrDefault(m.group(3), i[0] + 1) - 1;
                        return;
                    }
                    boolean jmp = false;
                    if (m.group(2) == null) {
                        jmp = true;
                    } else {
                        switch (m.group(2)) {
                            case "big":
                                jmp = getCmpFlag() > 0;
                                break;
                            case "lil":
                                jmp = getCmpFlag() < 0;
                                break;
                            case "dumb":
                                jmp = getCmpFlag() != 0;
                                break;
                            case "bad":
                                jmp = getCmpFlag() == 0;
                                break;
                        }
                    }
                    if (jmp) i[0] = labels.getOrDefault(m.group(3), i[0] + 1) - 1;
                }));
                else if (match(line, "^(?<num>(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?) on my wrist", m -> {
                    push(Double.parseDouble(m.group("num")));
                }));
                else if (match(line, "I just broke my wrist", m -> {
                    pop();
                }));
                else if (match(line, "Yuh", m -> {
                    compare(getAcc(), peek());
                }));
                else if (match(line, "Esskeetit", m -> {
                    popToAcc();
                }));
                else if (match(line, "None of this shit be new to me", m -> {
                    dupe();
                }));
                else if (match(line, "Gucci gang", m -> {
                    increment();
                }));
                else if (match(line, "Brrr", m -> {
                    decrement();
                }));
                else if (match(line, "I can move these bricks", m -> {
                    add();
                }));
                else if (match(line, "Smokepurpp", m -> {
                    sub();
                }));
                else if (match(line, "My lean cost more than your rent", m -> {
                    mul();
                }));
                else if (match(line, "Me and my grandma take meds", m -> {
                    div();
                }));
                else if (match(line, "I been had commando", m -> {
                    mod();
                }));
                else if (match(line, "Only wear designer", m -> {
                    pushAcc();
                }));
                else if (match(line, "Walk in the trap like a boss", m -> {
                    popToExt();
                }));
                else if (match(line, "I came in with the sauce", m -> {
                    pushExt();
                }));
                else if (match(line, "I been poppin (\\S+) since I was (born|(\\d+))", m -> {
                    int pop;
                    if (m.group(2).equals("born")) pop = 0;
                    else pop = Integer.parseInt(m.group(2));
                    int to = subroutines.get(m.group(1));
                    enterFrame(i[0], pop);
                    i[0] = to;
                }));
                else if (match(line, "(\\S+) love do cocaine", m -> {
                    skipSub[0] = true;
                }));
                else if (match(line, "Ooh", m -> {
                    i[0] = exitFrame();
                }));
                else if (match(line, "Huh", m -> {
                    i[0] = exitFrame(false);
                }));
            }

        } catch (Exception e) {
            System.err.println("fuck up on line " + i[0]);
            e.printStackTrace();
        }
    }

    static boolean match(String str, String regex, Consumer<Matcher> consumer) {
        Matcher m = Pattern.compile(regex).matcher(str);
        if (m.find()) {
            consumer.accept(m);
            return true;
        }
        return false;
    }
}
