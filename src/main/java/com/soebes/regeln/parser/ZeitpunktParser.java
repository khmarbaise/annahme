package com.soebes.regeln.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import com.soebes.regeln.annahme.Zeitraum;

public class ZeitpunktParser {
    private Calendar calendar = Calendar.getInstance(Locale.GERMANY);

    private static final Pattern DATE_PATTERN_MONTH_YEAR = Pattern.compile("\\d{1,2}\\.\\d{4}");
    private static final Pattern DATE_PATTERN_YEAR = Pattern.compile("\\d{4}");
    
    public Zeitraum parse(String zeitraum) throws UngueltigesDatumException, UngueltigesDatumFormatException {
        String[] zeitPunkte = zeitraum.trim().split("-");

        Date von = parseVon(zeitPunkte[0]);

        Date bis = parseBis(zeitPunkte[1]);
        
        return new Zeitraum(von, bis);
        
    }

    public Date parseVon(String vonAngabe) throws UngueltigesDatumException, UngueltigesDatumFormatException {
        Date result = null;

        if (DATE_PATTERN_MONTH_YEAR.matcher(vonAngabe).matches()) {
            result = parseMonatJahr(vonAngabe);
        } else if (DATE_PATTERN_YEAR.matcher(vonAngabe).matches()) {
            result = parseJahr(vonAngabe);
        } else {
            throw new UngueltigesDatumFormatException("Das angegebene Datum hat ein ungueltiges format.");
        }

        return result;
    }

    public Date parseBis(String bisAngabe) throws UngueltigesDatumException, UngueltigesDatumFormatException {
        Date result = null;

        if (DATE_PATTERN_MONTH_YEAR.matcher(bisAngabe).matches()) {
            result = parseMonatJahr(bisAngabe);

            Calendar source = Calendar.getInstance(Locale.GERMANY);
            source.setTime(result);

            int month = source.get(Calendar.MONTH);
            int year = source.get(Calendar.YEAR);
            int maxDay = source.getActualMaximum(Calendar.DAY_OF_MONTH);

            setzeTagMonatJahr(month, year, maxDay);

            result = calendar.getTime();
        } else if (DATE_PATTERN_YEAR.matcher(bisAngabe).matches()) {
            int year = Integer.parseInt(bisAngabe);

            setzeTagMonatJahr(Calendar.DECEMBER, year, 31);
            result = calendar.getTime();
        } else {
            throw new UngueltigesDatumFormatException("Das angegebene Datum hat ein ungueltiges format.");
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
            formatter.setLenient(false);
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
            formatter.setLenient(false);
            result = formatter.parse(content);
        } catch (ParseException e) {
            throw new UngueltigesDatumException("Datumsformat ist ungültig!");
        }
        return result;
    }

}
