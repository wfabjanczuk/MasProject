package mas.model;

public enum SkatesServiceTimeValidation {
    SUCCESS,
    DATE_START_IS_NULL,
    DATE_START_AFTER_DATE_END,
    OVERLAPPING_SERVICE_EXISTS
}
