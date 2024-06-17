package io.igventurelli;

/**
 * Regular Functional Interface that allows throwing Exceptions
 * @param <T> Input type
 * @param <R> Return type
 * @param <E> Throwable
 * @since 1.0.0
 * @author <a href="https://igventurelli.io">Igor Venturelli</a>
 */
@FunctionalInterface
public interface ThrowableFunction<T, R, E extends Throwable> {

    R apply(T t) throws E;
}
