package by.bulaukin.search_engine.model.exception;

public class UniqueConstraintViolationException extends Throwable {
    public UniqueConstraintViolationException(String s, Throwable mostSpecificCause) {
        super(s, mostSpecificCause);
    }
}
