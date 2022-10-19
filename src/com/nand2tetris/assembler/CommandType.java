package com.nand2tetris.assembler;

public enum CommandType {
    /**
     * for @Xxx where Xxx is either a symbol or a decimal number
     * */
    A_COMMAND,
    /**
     * for dest=comp;jump
     * */
    C_COMMAND,
    /**
     * for (Xxx) where Xxx is a symbol (actually, a pseudo-command--generate no machine code).
     * */
    L_COMMAND
}
