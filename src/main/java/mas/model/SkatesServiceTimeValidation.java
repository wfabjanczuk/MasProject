package mas.model;

public enum SkatesServiceTimeValidation {
    SUCCESS,
    DATE_START_IS_NULL,
    ZERO_TIME_DIFFERENCE,
    DATE_START_AFTER_DATE_END,
    OVERLAPPING_SERVICE_EXISTS
}
