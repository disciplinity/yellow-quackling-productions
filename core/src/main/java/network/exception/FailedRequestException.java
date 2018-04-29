package network.exception;

public class FailedRequestException  extends RuntimeException {

    public FailedRequestException(String message) {
        super(message);
    }

    public FailedRequestException() {}
}
