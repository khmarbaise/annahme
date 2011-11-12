package com.soebes.regeln.annahme;

public class VersionsBereich {

    private int von;
    private int bis;

    public VersionsBereich(int von, int bis) throws VersionsBereichVonBisVertauschtExepction {
        super();
        if (von > bis) {
            throw new VersionsBereichVonBisVertauschtExepction("Es muss die Bedinung von <= bis gelten.");
        }
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + bis;
        result = prime * result + von;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof VersionsBereich)) {
            return false;
        }
        VersionsBereich other = (VersionsBereich) obj;
        if (bis != other.bis) {
            return false;
        }
        if (von != other.von) {
            return false;
        }
        return true;
    }
    
    
}
