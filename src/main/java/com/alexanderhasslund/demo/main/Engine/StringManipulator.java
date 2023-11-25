package com.alexanderhasslund.demo.main.Engine;

public class StringManipulator {

    public String manipulateString(String string)  {
        try {
            for (char c : string.toCharArray()) {
                System.out.print(c);
                Thread.sleep(30);
            }
            return string;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }



    // try to print a YOU DIED - dark souls grej...
}
