package requester;

public interface Validator<T> {

    boolean isValid(T value);

}
