package com.nand2tetris.assembler;

import java.util.Map;

/**
 * Keeps a correspondence between symbolic labels and numeric addresses.
 * */
public interface SymbolTable {

    /**
     * Adds the pair (symbol, address) to the table.
     * */
    void addEntry(String symbol, int address);

    /**
     * Does the symbol table contain the given symbol?
     * */
    boolean contains(String symbol);

    /**
     * @return the address associated with the symbol.
     * */
    int getAddress(String symbol);

}
