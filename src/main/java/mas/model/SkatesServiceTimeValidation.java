package mas.model;

public enum SkatesServiceTimeValidation {
    SUCCESS,
    DATE_START_IS_NULL,
    ZERO_TIME_DIFFERENCE,
    DATE_END_BEFORE_DATE_START,
    OVERLAPPING_SERVICE_EXISTS
}
