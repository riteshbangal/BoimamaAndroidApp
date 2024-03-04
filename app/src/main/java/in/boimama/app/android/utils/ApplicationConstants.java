package in.boimama.app.android.utils;

public class ApplicationConstants {

    // Private constructor to prevent external instantiation
    private ApplicationConstants() {
    }

    // Define constant fields here

    public static final String EMPTY_STRING = "";
    public static final String SUCCESS_MESSAGE = "success";
    public static final String FAILURE_MESSAGE = "failure";

    // Singleton instance holder
    private static final ApplicationConstants instance = new ApplicationConstants();

    // Public static method to access the singleton instance
    public static ApplicationConstants getInstance() {
        return instance;
    }
}
