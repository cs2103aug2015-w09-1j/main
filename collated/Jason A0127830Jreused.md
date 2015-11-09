# Jason A0127830Jreused
###### src\test\UiTest.java
``` java
	  @Override
	  public void setupStage() throws Throwable {
	    assumeTrue(!UserInputDetector.instance.hasDetectedUserInput());

	    FXTestUtils.launchApp(TestUI.class); // You can add start parameters here
	    try {
	      stage = targetWindow(stageFuture.get(25, TimeUnit.SECONDS));
	      FXTestUtils.bringToFront(stage);
	    } catch (Exception e) {
	      throw new RuntimeException("Unable to show stage", e);
	    }
	    ((TextField) find("#text-field")).clear();
	  }

	  @Override
	  protected Parent getRootNode() {
	    return stage.getScene().getRoot();
	  }
	  
```
###### src\util\DateUtil.java
``` java
 */
public class DateUtil {

    /** The date pattern that is used for conversion. Change as you wish. */
    private static final String DATE_PATTERN = "ddMMyyyy";

    /** The date formatter. */
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Returns the given date as a well formatted String. The above defined 
     * {@link DateUtil#DATE_PATTERN} is used.
     * 
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Converts a String in the format of the defined {@link DateUtil#DATE_PATTERN} 
     * to a {@link LocalDate} object.
     * 
     * Returns null if the String could not be converted.
     * 
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks the String whether it is a valid date.
     * 
     * @param dateString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }
}
```
