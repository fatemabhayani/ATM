package phase2;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The ATM time.
 */
public class ATMTime {
    /**
     * The single ATM clock.
     */
    private static ATMTime clock;

    /**
     * The factor added to the year.
     */
    private int yearFactor;

    /**
     * The factor added to the month.
     */
    private int monthFactor;

    /**
     * The factor added to the day.
     */
    private int dayFactor;

    /**
     * The factor added to the hour.
     */
    private int hourFactor;

    /**
     * The factor added to the minute.
     */
    private int minuteFactor;

    /**
     * The factor added to the second.
     */
    private int secondFactor;

    /**
     * Creates a new clock for the ATM.
     *
     * @param year the year
     * @param month the month
     * @param day the day
     * @param hour the hour
     * @param minute the minute
     * @param second the second
     */
    private ATMTime(int year, int month, int day, int hour, int minute, int second) {
        setDate(year, month, day, hour, minute, second);
    }

    public static ATMTime getInstance() {
        if (clock == null) {
            clock = new ATMTime(2019, 0, 1, 0, 0, 0);
        }
        return clock;
    }

    /**
     * Returns the day.
     */
    private int getCurrentDay() {
        return getCurrentTime().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Returns a calendar timestamp of the current time.
     *
     * @return the timestamp for the current time
     */
    public Calendar getCurrentTime() {
        Calendar date = new GregorianCalendar();
        date.add(Calendar.YEAR, yearFactor);
        date.add(Calendar.MONTH, monthFactor);
        date.add(Calendar.DAY_OF_MONTH, dayFactor);
        date.add(Calendar.HOUR_OF_DAY, hourFactor);
        date.add(Calendar.MINUTE, minuteFactor);
        date.add(Calendar.SECOND, secondFactor);
        return date;
    }

    /**
     * Returns whether or not is it the first of the month at midnight.
     *
     * @return true iff it is the first of the month
     */
    public boolean isFirstOfMonth() {
        return (getCurrentDay() == 1);
    }

    /**
     * Sets the date and time.
     *
     * @param year the year
     * @param month the month
     * @param day the day
     * @param hour the hour
     * @param minute the minute
     * @param second the second
     */
    public void setDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar date = new GregorianCalendar();

        yearFactor = year - date.get(Calendar.YEAR);
        monthFactor = month - date.get(Calendar.MONTH);
        dayFactor = day - date.get(Calendar.DAY_OF_MONTH);
        hourFactor = hour - date.get(Calendar.HOUR_OF_DAY);
        minuteFactor = minute - date.get(Calendar.MINUTE);
        secondFactor = second - date.get(Calendar.SECOND);
    }

    /**
     * Sets the date and time.
     *
     * @param factors the list of factors
     */
    public void setFactors(int[] factors) {
        this.yearFactor = factors[0];
        this.monthFactor = factors[1];
        this.dayFactor = factors[2];
        this.hourFactor = factors[3];
        this.minuteFactor = factors[4];
        this.secondFactor = factors[5];
    }

    @Override
    public String toString() {
        return yearFactor + " " + monthFactor + " " + dayFactor + " " + hourFactor + " " + minuteFactor + " " + secondFactor;
    }

}
