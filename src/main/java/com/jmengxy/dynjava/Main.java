package com.jmengxy.dynjava;

import com.jmengxy.dynjava.dynamics.DynamicInterpreter;
import com.jmengxy.dynjava.dynamics.JRuby;
import com.jmengxy.dynjava.dynamics.Jython;
import com.sun.tools.javac.util.Pair;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("\npython");
        main.test(new Jython(), main.generatePythonScript());

        System.out.println("\nruby");
        main.test(new JRuby(), main.generateRubyScript());
    }

    private void test(DynamicInterpreter interpreter, String script) {
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

        interpreter.close();
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
}
