package phase2;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The ATM time.
 */
public class ATMTime {
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
    public ATMTime(int year, int month, int day, int hour, int minute, int second) {
        setDate(year, month, day, hour, minute, second);
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
        date.add(Calendar.HOUR, hourFactor);
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
        hourFactor = hour - date.get(Calendar.HOUR);
        minuteFactor = minute - date.get(Calendar.MINUTE);
        secondFactor = second - date.get(Calendar.SECOND);
    }

    @Override
    public String toString() {
        Calendar date = getCurrentTime();
        return date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.DAY_OF_MONTH) +
                " " + date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE) + ":" +
                date.get(Calendar.SECOND);
    }

}
