package com.soebes.regeln.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZeitpunktParser {
//            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Date parse(String content) throws UngueltigesDatumException {
        Date result = null;
        try {
            result = parseMonatJahr(content);
        } catch (UngueltigesDatumException e) {
            try {
                result = parseJahr(content);
            } catch (UngueltigesDatumException e1) {
                throw new UngueltigesDatumException(e1);
            }
        }
        return result;
    }

    private Date parseJahr(String content) throws UngueltigesDatumException {
        Date result = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy");
            result = formatter.parse(content);
        } catch (ParseException e) {
            throw new UngueltigesDatumException("Datumsformat ist ungültig!");
        }
        return result;
    }

    
    private Date parseMonatJahr(String content) throws UngueltigesDatumException {
        Date result = null;
        try {
            DateFormat formatter = new SimpleDateFormat("MM.yyyy");
            result = formatter.parse(content);
        } catch (ParseException e) {
            throw new UngueltigesDatumException("Datumsformat ist ungültig!");
        }
        return result;
    }

}
