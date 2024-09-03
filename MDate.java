package project.pkg1;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Serra Ozturk
 */
public class MDate {

    private int day;
    private int month;
    private int year;

    public MDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        Calendar cal = new GregorianCalendar(year, month, day);
        Date dt = cal.getTime();
        String result = dt.toString();
        return result;
    }

}
