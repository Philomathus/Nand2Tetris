package com.nand2tetris.assembler;

/**
 * Translates Hack assembly language mnemonics into binary codes.
 * */
public interface Code {

    /**
     * @return the binary code of the dest mnemonic.
     * */
    String dest(String mnemonic);

    /**
     * @return the binary code of the comp mnemonic.
     * */
    String comp(String mnemonic);

    /**
     * @return the binary code of the jump mnemonic.
     * */
    String jump(String mnemonic);

}
