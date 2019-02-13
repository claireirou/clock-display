
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before
 * midnight).
 *
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class ClockDisplay12Hr
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private boolean meridian;        // determines am (true) or pm (false) 

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at 00:00.
     */
    public ClockDisplay12Hr()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        meridian = true;
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the
     * parameters.
     */
    public ClockDisplay12Hr(int hour, int minute, boolean AM)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        meridian = AM;
        setTime(hour, minute, AM);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
            if(hours.getValue() == 0) { // hours rolled over
                meridian = !meridian; 
            }
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, boolean AM)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        meridian = AM;
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }

    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        /**
         * Conditional statement to test whether it's AM or PM 
         * and to print out accordingly.
         */
        if(meridian == true) {
            /**
             * Conditional statement to test if hour is 0.
             * If it is, prints out 12 instead followed by
             * minutes and meridian value.
             */
            if(hours.getValue() == 0) {
                displayString = 12 + ":" +
                        minutes.getDisplayValue() + "AM";
            }else{
                displayString = hours.getDisplayValue() + ":" +
                        minutes.getDisplayValue() + "AM";
            }
            
        }else{
            
            if(hours.getValue() == 0) {
                displayString = 12 + ":" +
                        minutes.getDisplayValue() + "PM";
            }else{
                displayString = hours.getDisplayValue() + ":" +
                        minutes.getDisplayValue() + "PM";
            }
            
        }
    }
}
