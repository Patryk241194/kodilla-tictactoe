package com.kodilla;

public class NPC {
    private String npcName;
    private char npcSymbol;

    public NPC(Player player) {
        this.npcName = "computer";
        if (player.getUserSymbol() == 'x') {
            this.npcSymbol = 'o';
        } else if (player.getUserSymbol() == 'o') {
            this.npcSymbol = 'x';
        }
    }

    public String getNpcName() {
        return npcName;
    }

    public void setNpcName(String npcName) {
        this.npcName = npcName;
    }

    public char getNpcSymbol() {
        return npcSymbol;
    }

    public void setNpcSymbol(char npcSymbol) {
        this.npcSymbol = npcSymbol;
    }

    @Override
    public String toString() {
        return "NPC{" +
                "npcName='" + npcName + '\'' +
                '}';
    }
}
