package com.nand2tetris.assembler;

/**
 * Encapsulates access to the input code.<br>
 * Reads an assembly language command, parses it, and provides convenient access to the command's components (fields and symbols).<br>
 * In addition, removes all whitespace and comments.
 * */
public interface Parser {

    boolean hasMoreCommands();

    /**
     * Reads the next command from the input and makes it the current command.<br>
     * Initially, there is no current command.<br>
     * Should only be called if hasMoreCommands() is true.
     * */
    void advance();

    /**
     * @return the type of the current command.
     * */
    CommandType commandType();

    /**
     * Should only be called when commandType is A_COMMAND or L_COMMAND.
     * */
    String symbol();

    /**
     * Should only be called when commandType() is C_COMMAND.
     * @return the dest mnemonic in the current C-command (8 possibilities).
     * */
    String dest();

    /**
     * Should only be called when commandType() is C_COMMAND.
     * @return the comp mnemonic in the current C-command (28 possibilities).
     * */
    String comp();

    /**
     * Should only be called when commandType() is C_COMMAND.
     * @return the jump mnemonic in the current C-command (8 possibilities).
     * */
    String jump();

}
