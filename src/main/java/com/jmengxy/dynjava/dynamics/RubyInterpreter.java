package com.jmengxy.dynjava.dynamics;

import com.sun.tools.javac.util.Pair;
import org.jruby.embed.LocalVariableBehavior;
import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

public class RubyInterpreter implements DynamicInterpreter {

    private ScriptingContainer interpreter;

    public RubyInterpreter() {
        interpreter = new ScriptingContainer(LocalVariableBehavior.PERSISTENT);
    }

    public void close() {
        if (interpreter != null) {
            interpreter.clear();
            interpreter = null;
        }
    }

    public String getString(String name, String defaultValue) {
        Object o = interpreter.get(name);
        return o instanceof String ? (String) o : defaultValue;
    }

    public int getInteger(String name, int defaultValue) {
        Object o = interpreter.get(name);
        if (o instanceof Integer) {
            return ((Integer) o).intValue();
        } else if (o instanceof Long) {
            return ((Long)o).intValue();
        } else {
            return defaultValue;
        }
    }

    public double getDouble(String name, double defaultValue) {
        Object o = interpreter.get(name);
        if (o instanceof Double) {
            return ((Double) o).doubleValue();
        } else if (o instanceof Float) {
            return ((Float)o).doubleValue();
        } else {
            return defaultValue;
        }
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        Object o = interpreter.get(name);
        return o instanceof Boolean ? ((Boolean) o).booleanValue() : defaultValue;
    }

    public void setString(String name, String value) {
        interpreter.put(name, value);
    }

    public void setInteger(String name, int value) {
        interpreter.put(name, value);
    }

    public void setDouble(String name, double value) {
        interpreter.put(name, value);
    }

    public void setBoolean(String name, boolean value) {
        interpreter.put(name, value);
    }

    public Pair<Boolean, String> parseLine(String line) {
        try {
            interpreter.runScriptlet(line);
            return Pair.of(true, "");
        } catch (Exception e) {
            return Pair.of(false, e.toString());
        }
    }

    public Pair<Boolean, String> parseFile(String file) {
        try {
            interpreter.runScriptlet(PathType.ABSOLUTE, file);
            return Pair.of(true, "");
        } catch (Exception e) {
            return Pair.of(false, e.toString());
        }
    }
}
