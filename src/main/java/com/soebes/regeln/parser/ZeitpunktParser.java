package com.soebes.regeln.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.IllegalFormatException;
import java.util.Locale;

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

    public Date parseBis(String content) throws UngueltigesDatumException {
        Date result = null;
        try {
            result = parseMonatJahr(content);
            Calendar source = Calendar.getInstance(Locale.GERMANY);
            source.setTime(result);
            int month = source.get(Calendar.MONTH);
            int year = source.get(Calendar.YEAR);
            int maxDay = source.getActualMaximum(Calendar.DAY_OF_MONTH);
            Calendar c = Calendar.getInstance(Locale.GERMANY);
            c.set(year, month, maxDay, 23, 59, 59);
            c.set(Calendar.MILLISECOND, 0);
            result = c.getTime();
        } catch (UngueltigesDatumException e) {
            try {
                int year = Integer.parseInt(content);
                Calendar c = Calendar.getInstance(Locale.GERMANY);
                c.set(year, 11, 31, 23, 59, 59);
                c.set(Calendar.MILLISECOND, 0);
                result = c.getTime();
            } catch (IllegalFormatException e1) {
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
