package com.nand2tetris;

import com.nand2tetris.assembler.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        Pattern cPattern = Pattern.compile("((?<dest>[AMD]+)=)?(?<comp>[-+!&|AMD1]+)(;(?<jump>[JMPLENTGQ]+))?");
        Matcher cMatcher = cPattern.matcher("AMD=D+1");
        if(cMatcher.matches()) {
            String dest = cMatcher.group("dest");
            String comp = cMatcher.group("comp");
            String jump = cMatcher.group("jump");

            System.out.println(dest);
            System.out.println(comp);
            System.out.println(jump);
        }

    }
}
