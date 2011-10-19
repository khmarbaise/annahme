package com.soebes.regeln.annahme;

public class VersionsBereich {

    private int von;
    private int bis;

    public VersionsBereich(int von, int bis) {
        super();
        this.von = von;
        this.bis = bis;
    }

    public boolean contains (int currentVersion) {
        if (currentVersion >= von && currentVersion <= bis) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getVon() {
        return von;
    }
    public void setVon(int von) {
        this.von = von;
    }
    public int getBis() {
        return bis;
    }
    public void setBis(int bis) {
        this.bis = bis;
    }
    
    
}
