package com.rcops.sqlite.manager;

public class EditPath {
    public static String ChangeBackSlashes(String path){
        return path.replace("\\", "/");
    }
}
