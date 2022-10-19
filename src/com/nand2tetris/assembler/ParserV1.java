package com.nand2tetris.assembler;

import java.io.*;
import java.util.IllegalFormatException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParserV1 implements Parser {

    private final Code code;
    private String currentCommmand;
    private String nextCommand;

    public ParserV1(String fileName) throws FileNotFoundException {

        if(!fileName.endsWith(".asm")) {
            throw new RuntimeException();
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        //causes significant overhead as the file gets larger
        commandsQueue = bufferedReader.lines()
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

        code = new CodeV1();
    }

    @Override
    public boolean hasMoreCommands() {
        return commandsQueue.isEmpty();
    }

    @Override
    public void advance() {

        //advance and parse
        //determine command type
        commandsQueue.poll();
    }

    @Override
    public CommandType commandType() {

        if(commandsQueue.peek().startsWith("@")) {
            return CommandType.A_COMMAND;
        }

        if(commandsQueue.peek().startsWith("(")) {

        }

        return null;
    }

    @Override
    public String symbol() {
        return null;
    }

    @Override
    public String dest() {
        Pattern pattern = Pattern.compile("(?:(.+?)=)?(.+?)(?:;([JEQGLTNMP]{3}))?");

        Matcher matcher = pattern.matcher(commandsQueue.peek());
        return code.dest();
    }

    @Override
    public String comp() {
        return null;
    }

    @Override
    public String jump() {
        return null;
    }
}
