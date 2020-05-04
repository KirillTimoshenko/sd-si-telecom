package com.netcracker.ec;

public class Main {
    public static void main(String[] arg){
        try {
            new Application().run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}