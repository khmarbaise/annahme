package com.soebes.regeln.annahme;

import java.util.Date;

public class Zeitraum {

    private Date von;
    private Date bis;
    
    public Zeitraum(Date von, Date bis) {
        super();
        this.von = von;
        this.bis = bis;
    }

    public boolean contains (Date current) {
        boolean result = false;
        
        if (current.after(getVon()) && current.before(getBis())) {
            result = true;
        }

        if (current.equals(getVon()) && current.before(getBis())) {
            result = true;
        }
        
        if (current.equals(getBis()) && current.after(getVon())) {
            result = true;
        }

        return result;
    }

    public Date getVon() {
        return von;
    }
    public void setVon(Date von) {
        this.von = von;
    }
    public Date getBis() {
        return bis;
    }
    public void setBis(Date bis) {
        this.bis = bis;
    }
    
    
}
