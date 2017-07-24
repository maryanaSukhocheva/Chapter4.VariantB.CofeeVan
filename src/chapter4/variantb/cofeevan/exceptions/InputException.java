package chapter4.variantb.cofeevan.exceptions;

/**
 * Исключение при передаче
 * нуля или отрицательного значения
 *
 */

public class InputException extends ProjectMainException {
    public InputException(String message) {
        super(message);
    }
}
