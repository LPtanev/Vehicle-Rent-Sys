package Solution.models.utils;

import Solution.models.exceptions.InvalidUserInputException;

public class ValidationHelper {

    public static final int SAFETY_RATING_MIN_VALUE = 1;

    public static final int SAFETY_RATING_MAX_VALUE = 5;

    public static void validateStringMinLength(String value) {
        if (value == null) {
            throw new InvalidUserInputException("Please enter correct value");
        }
    }

    public static void validateGreaterThanZero(double value) {
        if (value <= 0) {
            throw new InvalidUserInputException("Please change value to be more then zero");
        }
    }

    public static void validateSafetyRatingValue(int value) {
        if (SAFETY_RATING_MIN_VALUE > value || value > SAFETY_RATING_MAX_VALUE) {
            throw new InvalidUserInputException(String.format("Safety rating should be between %d and %d",
                    SAFETY_RATING_MIN_VALUE, SAFETY_RATING_MAX_VALUE));
        }
    }
    public static void validateAge(int value) {
        if (value <= 18) {
            throw new InvalidUserInputException("Only persons older then 18 year age are allowed to drive");
        }
    }
}
