package in.boimama.app.android.utils;


import java.util.Collection;

/**
 * Utility methods for the application
 */
public class ApplicationUtils {

    public static boolean isEmpty(Object pObject) {
        return pObject == null;
    }

    public static boolean isEmpty(Collection<?> pCollectionObject) {
        return pCollectionObject == null || pCollectionObject.isEmpty();
    }

    // Prevent instantiation as this is a utility class.
    private ApplicationUtils() {
        throw new UnsupportedOperationException("Cannot instantiate utility class");
    }
}
