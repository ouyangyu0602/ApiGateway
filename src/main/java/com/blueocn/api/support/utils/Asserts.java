package com.blueocn.api.support.utils;

/**
 * Title: Assert
 * Description: I love guava, but it lacks some helper function for String.
 * So I have to added it here.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-05 23:32
 */
public final class Asserts {

    private Asserts() {
        // No Construct function
    }

    /**
     * Ensures that a String reference passed as a parameter to the calling method is not blank.
     *
     * @param reference an String reference
     * @return the non-blank reference that was validated
     * @throws IllegalArgumentException if {@code reference} is blank
     */
    public static String checkNotBlank(String reference) {
        return checkNotBlank(reference, null);
    }

    /**
     * Ensures that a String reference passed as a parameter to the calling method is not blank.
     * 1. "  ", IllegalArgumentException
     * 2. "", IllegalArgumentException
     * 3. null, IllegalArgumentException
     * 4. " aaa ", no Exception
     * 5. "aaa", no Exception
     *
     * @param reference    an String reference
     * @param errorMessage the exception message to use if the check fails; will be converted to a
     *                     string using {@link String#valueOf(Object)}
     * @return the non-blank reference that was validated
     * @throws IllegalArgumentException if {@code reference} is blank
     */
    public static String checkNotBlank(String reference, Object errorMessage) {
        if (reference == null || reference.trim().length() == 0) {
            throwIllegalArgumentException(errorMessage);
        }
        return reference;
    }

    /**
     * Ensures that a String reference passed as a parameter to the calling method is not empty.
     *
     * @param reference an String reference
     * @return the non-empty reference that was validated
     * @throws IllegalArgumentException if {@code reference} is empty
     */
    public static String checkNotEmpty(String reference) {
        return checkNotEmpty(reference, null);
    }

    /**
     * Ensures that a String reference passed as a parameter to the calling method is not empty.
     * 1. "  ", no Exception
     * 2. "", IllegalArgumentException
     * 3. null, IllegalArgumentException
     * 4. " aaa ", no Exception
     * 5. "aaa", no Exception
     *
     * @param reference    an String reference
     * @param errorMessage the exception message to use if the check fails; will be converted to a
     *                     string using {@link String#valueOf(Object)}
     * @return the non-empty reference that was validated
     * @throws IllegalArgumentException if {@code reference} is empty
     */
    public static String checkNotEmpty(String reference, Object errorMessage) {
        if (reference == null || reference.length() == 0) {
            throwIllegalArgumentException(errorMessage);
        }
        return reference;
    }

    private static void throwIllegalArgumentException(Object errorMessage) {
        throw errorMessage == null ? new IllegalArgumentException() : new IllegalArgumentException(String.valueOf(errorMessage));
    }
}
