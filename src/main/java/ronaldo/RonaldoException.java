package ronaldo;

/**
 * Custom exceptions for the Ronaldo application.
 */
class RonaldoException extends Exception {

    /**
     * Constructs a new RonaldoException with the specified message.
     *
     * @param message A description of the exception.
     */
    public RonaldoException(String message) {
        super(message);
    }
}
