package com.nand2tetris.assembler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

public class Assembler {

    private SymbolTable symbolTable;
    private Parser parser;
    private Code code;

    public Assembler() {

        //Input default addresses
        symbolTable = new SymbolTableV1();

        symbolTable.addEntry("SP", 0);
        symbolTable.addEntry("LCL", 1);
        symbolTable.addEntry("ARG", 2);
        symbolTable.addEntry("THIS", 3);
        symbolTable.addEntry("THAT", 4);
        symbolTable.addEntry("SCREEN", 16384);
        symbolTable.addEntry("KBD", 24576);

        for(int i = 0; i < 16; ++i) {
            symbolTable.addEntry("R" + i, i);
        }

    }

    public void compile(String srcPath, String destPath) throws IOException {

        FileWriter destination = new FileWriter(destPath);
        Parser parser = new ParserV1(srcPath);

        //populate symbol table
        int programCounter = 0;
        while(parser.hasMoreCommands()) {

            parser.advance();

            if(parser.commandType() == CommandType.L_COMMAND) {
                symbolTable.addEntry(parser.symbol(), programCounter);
            } else if(parser.commandType() == null) {
                throw new RuntimeException();
            }

        }

        parser = new ParserV1(srcPath);
        int variableAddress = 16;
        while(parser.hasMoreCommands()) {

            switch (parser.commandType()) {
                case A_COMMAND:

                    if(!symbolTable.contains(parser.symbol())) {
                        symbolTable.addEntry(parser.symbol(), variableAddress++);
                    }

                    destination.write(String.format("%16s", Integer.toBinaryString(symbolTable.getAddress(parser.symbol()))).replace(' ', '0'));

                    break;
                case C_COMMAND:



                    break;
                case L_COMMAND:



                    break;
                default:
                    throw new RuntimeException();
            }

        }

    }


}
