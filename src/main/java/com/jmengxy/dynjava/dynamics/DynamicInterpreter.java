package com.jmengxy.dynjava.dynamics;

import com.sun.tools.javac.util.Pair;

public interface DynamicInterpreter {

    void close();

    String getString(String name, String defaultValue);
    int getInteger(String name, int defaultValue);
    double getDouble(String name, double defaultValue);
    boolean getBoolean(String name, boolean defaultValue);

    void setString(String name, String value);
    void setInteger(String name, int value);
    void setDouble(String name, double value);
    void setBoolean(String name, boolean value);

    Pair<Boolean, String> parseLine(String line);
    Pair<Boolean, String> parseFile(String file);
}
