package com.jmengxy.dynjava.dynamics;

import com.sun.tools.javac.util.Pair;
import groovy.lang.GroovyShell;

import java.io.File;

public class GroovyInterpreter implements DynamicInterpreter {

    private GroovyShell interpreter;

    public GroovyInterpreter() {
        interpreter = new GroovyShell();
    }

    public void close() {
    }

    public String getString(String name, String defaultValue) {
        Object o = interpreter.getVariable(name);
        if (o instanceof String) {
            return (String)o;
        } else {
            return defaultValue;
        }
    }

    public int getInteger(String name, int defaultValue) {
        Object o = interpreter.getVariable(name);
        if (o instanceof Integer) {
            return (Integer) o;
        } else if (o instanceof Long) {
            return ((Long) o).intValue();
        } else {
            return defaultValue;
        }
    }

    public double getDouble(String name, double defaultValue) {
        Object o = interpreter.getVariable(name);
        if (o instanceof Double) {
            return (Double) o;
        } else if (o instanceof  Float) {
            return ((Float) o).doubleValue();
        } else {
            return defaultValue;
        }
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        Object o = interpreter.getVariable(name);
        if (o instanceof Boolean) {
            return (Boolean) o;
        } else {
            return defaultValue;
        }
    }

    public void setString(String name, String value) {
        interpreter.setVariable(name, value);
    }

    public void setInteger(String name, int value) {
        interpreter.setVariable(name, value);
    }

    public void setDouble(String name, double value) {
        interpreter.setVariable(name, value);
    }

    public void setBoolean(String name, boolean value) {
        interpreter.setVariable(name, value);
    }

    public Pair<Boolean, String> parseLine(String line) {
        try {
            interpreter.evaluate(line);
            return Pair.of(true, "");
        } catch (Exception e) {
            return Pair.of(false, e.toString());
        }
    }

    public Pair<Boolean, String> parseFile(String file) {
        try {
            interpreter.evaluate(new File(file));
            return Pair.of(true, "");
        } catch (Exception e) {
            return Pair.of(false, e.toString());
        }
    }
}
