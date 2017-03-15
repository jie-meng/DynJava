package com.jmengxy.dynjava.dynamics;

import com.sun.tools.javac.util.Pair;
import org.python.core.*;
import org.python.util.PythonInterpreter;

import java.util.Properties;

public class Jython implements DynamicInterpreter {

    private PythonInterpreter interpreter;

    public Jython() {
        //http://bugs.jython.org/issue2355
        Properties props = new Properties();
        props.put("python.home","path to the Lib folder");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site","false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);

        interpreter = new PythonInterpreter();
    }

    public void close() {
        if (interpreter != null) {
            interpreter.close();
            interpreter = null;
        }
    }

    public String getString(String name, String defaultValue) {
        PyObject pyObject = interpreter.get(name);
        if (pyObject instanceof PyString) {
            return ((PyString)pyObject).getString();
        } else {
            return defaultValue;
        }
    }

    public int getInteger(String name, int defaultValue) {
        PyObject pyObject = interpreter.get(name);
        if (pyObject instanceof PyInteger) {
            return ((PyInteger)pyObject).getValue();
        } else {
            return defaultValue;
        }
    }

    public double getDouble(String name, double defaultValue) {
        PyObject pyObject = interpreter.get(name);
        if (pyObject instanceof PyFloat) {
            return ((PyFloat)pyObject).getValue();
        } else {
            return defaultValue;
        }
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        PyObject pyObject = interpreter.get(name);
        if (pyObject instanceof PyBoolean) {
            return ((PyBoolean)pyObject).getBooleanValue();
        } else {
            return defaultValue;
        }
    }

    public void setString(String name, String value) {
        interpreter.set(name, value);
    }

    public void setInteger(String name, int value) {
        interpreter.set(name, value);
    }

    public void setDouble(String name, double value) {
        interpreter.set(name, value);
    }

    public void setBoolean(String name, boolean value) {
        interpreter.set(name, value);
    }

    public Pair<Boolean, String> parseLine(String line) {
        try {
            interpreter.exec(line);
            return Pair.of(true, "");
        } catch (Exception e) {
            return Pair.of(false, e.toString());
        }
    }

    public Pair<Boolean, String> parseFile(String file) {
        try {
            interpreter.execfile(file);
            return Pair.of(true, "");
        } catch (Exception e) {
            return Pair.of(false, e.toString());
        }
    }
}
