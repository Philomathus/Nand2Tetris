package com.nand2tetris.assembler;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParserV1 implements Parser {

    private final static String SYMBOL_PATTERN = "[a-zA-Z_.$:][a-zA-Z_.$:0-9]*";

    private Queue<String> commandsQueue;
    private CommandType currentCommandType;
    private String symbol;
    private String dest;
    private String comp;
    private String jump;

    public ParserV1(String fileName) throws FileNotFoundException {

        if(!fileName.endsWith(".asm")) {
            throw new RuntimeException();
        }

        //causes significant overhead as the file gets larger
        commandsQueue = new BufferedReader(new FileReader(fileName))
            .lines()
            .map(line -> {

                //remove comments
                int commentIndex = line.indexOf("//");

                if(commentIndex > -1) {
                    line = line.substring(0, commentIndex);
                }

                return line.trim();

            })
            .filter(line -> !line.isEmpty())
            .collect(Collectors.toCollection(LinkedList<String>::new));

    }

    @Override
    public boolean hasMoreCommands() {
        return !commandsQueue.isEmpty();
    }

    @Override
    public void advance() {

        currentCommandType = null;
        symbol = null;
        dest = null;
        jump = null;
        comp = null;

        String currentCommand = commandsQueue.poll();

        Pattern aPattern = Pattern.compile("@(" + SYMBOL_PATTERN + ")");
        Matcher aMatcher = aPattern.matcher(currentCommand);
        if(aMatcher.matches()) {
            symbol = aMatcher.group();
            currentCommandType = CommandType.A_COMMAND;
            return;
        }

        Pattern lPattern = Pattern.compile("\\(("+ SYMBOL_PATTERN +")\\)");
        Matcher lMatcher = lPattern.matcher(currentCommand);
        if(lMatcher.matches()) {
            symbol = lMatcher.group();
            currentCommandType = CommandType.L_COMMAND;
            return;
        }

        Pattern cPattern = Pattern.compile("((?<dest>[AMD]{3})=)?(?<comp>[-+!&|01AMD]{1,3})(;(?<jump>[JMPLENTGQ]{3}))?");
        Matcher cMatcher = cPattern.matcher(currentCommand);
        if(cMatcher.matches()) {
            dest = cMatcher.group("dest");
            comp = cMatcher.group("comp");
            jump = cMatcher.group("jump");
            currentCommandType = CommandType.C_COMMAND;
        }

    }

    @Override
    public CommandType commandType() {
        return currentCommandType;
    }

    @Override
    public String symbol() {
        return symbol;
    }

    @Override
    public String dest() {
        return dest;
    }

    @Override
    public String comp() {
        return comp;
    }

    @Override
    public String jump() {
        return jump;
    }

}
