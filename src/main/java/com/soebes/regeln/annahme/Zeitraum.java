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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bis == null) ? 0 : bis.hashCode());
        result = prime * result + ((von == null) ? 0 : von.hashCode());
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
        if (!(obj instanceof Zeitraum)) {
            return false;
        }
        Zeitraum other = (Zeitraum) obj;
        if (bis == null) {
            if (other.bis != null) {
                return false;
            }
        } else if (!bis.equals(other.bis)) {
            return false;
        }
        if (von == null) {
            if (other.von != null) {
                return false;
            }
        } else if (!von.equals(other.von)) {
            return false;
        }
        return true;
    }

    
    
}
