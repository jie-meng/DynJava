package com.jmengxy.dynjava;

import com.jmengxy.dynjava.dynamics.DynamicInterpreter;
import com.jmengxy.dynjava.dynamics.GroovyInterpreter;
import com.jmengxy.dynjava.dynamics.RubyInterpreter;
import com.jmengxy.dynjava.dynamics.PyInterpreter;
import com.sun.tools.javac.util.Pair;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("\npython");
        main.test(new PyInterpreter(), main.generatePythonScript());

        System.out.println("\nruby");
        main.test(new RubyInterpreter(), main.generateRubyScript());

        System.out.println("\ngroovy");
        main.test(new GroovyInterpreter(), main.generateGroovyScript());
    }

    private void test(DynamicInterpreter interpreter, String script) {
        try
        {
            interpreter.setString("s", "test");
            interpreter.setInteger("i", 21);
            interpreter.setDouble("d", 3.14);
            interpreter.setBoolean("b", false);

            Pair<Boolean, String> pair = interpreter.parseLine(script);
            if (pair.fst) {
                System.out.println("s == " + interpreter.getString("s", ""));
                System.out.println("i == " + interpreter.getInteger("i", 0));
                System.out.println("d == " + interpreter.getDouble("d", 0.01));
                System.out.println("b == " + interpreter.getBoolean("b", false));
            } else {
                System.err.println("--- Error ---");
                System.err.println(pair.snd);
            }
        } finally {
            interpreter.close();
        }
    }

    private String generatePythonScript() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("s = s + '..ok'\n");
        buffer.append("i += 100\n");
        buffer.append("d += 1000\n");
        buffer.append("b = True\n");

        return buffer.toString();
    }

    private String generateRubyScript() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("s = s + '..ok'\n");
        buffer.append("i = i + 100\n");
        buffer.append("d = d + 1000\n");
        buffer.append("b = true\n");

        return buffer.toString();
    }

    private String generateGroovyScript() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("s = s + '..ok'\n");
        buffer.append("i = i + 100\n");
        buffer.append("d = d + 1000\n");
        buffer.append("b = true\n");

        return buffer.toString();
    }
}
