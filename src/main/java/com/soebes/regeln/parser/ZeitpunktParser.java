package com.soebes.regeln.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.IllegalFormatException;
import java.util.Locale;

public class ZeitpunktParser {
    private Calendar calendar = Calendar.getInstance(Locale.GERMANY);

    public Date parseVon(String vonAngabe) throws UngueltigesDatumException {
        Date result = null;
        try {
            result = parseMonatJahr(vonAngabe);
        } catch (UngueltigesDatumException e) {
            try {
                result = parseJahr(vonAngabe);
            } catch (UngueltigesDatumException e1) {
                throw new UngueltigesDatumException(e1);
            }
        }
        return result;
    }

    public Date parseBis(String bisAngabe) throws UngueltigesDatumException {
        Date result = null;

        try {
            result = parseMonatJahr(bisAngabe);

            Calendar source = Calendar.getInstance(Locale.GERMANY);
            source.setTime(result);

            int month = source.get(Calendar.MONTH);
            int year = source.get(Calendar.YEAR);
            int maxDay = source.getActualMaximum(Calendar.DAY_OF_MONTH);

            setzeTagMonatJahr(month, year, maxDay);

            result = calendar.getTime();
        } catch (UngueltigesDatumException e) {
            try {
                int year = Integer.parseInt(bisAngabe);

                setzeTagMonatJahr(Calendar.DECEMBER, year, 31);
                result = calendar.getTime();
            } catch (IllegalFormatException e1) {
                throw new UngueltigesDatumException(e1);
            }
        }
        return result;
    }

    private void setzeTagMonatJahr(int month, int year, int maxDay) {
        calendar.set(year, month, maxDay, 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
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
