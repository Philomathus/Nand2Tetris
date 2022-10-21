package com.nand2tetris.assembler;

import java.util.Hashtable;

public class SymbolTableV1 implements SymbolTable {

    private Hashtable<String, Integer> symbolToValue = new Hashtable<>();
//    private int symbolCounter = 16;

    @Override
    public void addEntry(String symbol, int address) {
        symbolToValue.put(symbol, address);
    }

    @Override
    public boolean contains(String symbol) {
        return symbolToValue.containsKey(symbol);
    }

    @Override
    public int getAddress(String symbol) {
        return symbolToValue.get(symbol);
    }


}
