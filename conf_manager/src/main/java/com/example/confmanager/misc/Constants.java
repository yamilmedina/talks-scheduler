package com.example.confmanager.misc;

public class Constants {

    public final static String VALID_INPUT = "^[^0-9^\\n]+(?:[0-9]*min|lightning)$";
    public final static String TITLE = "((\\d)+min*|lightning)";
    public final static String TIME = "^([^0-9^\\n]*)(?=([0-9]+min|lightning)$)";
    public final static String WORKING_DIR = System.getProperty("user.dir");
}
