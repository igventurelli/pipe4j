package io.igventurelli;

import java.util.Objects;
import java.util.function.Function;

/**
 * Build a pipeline, handling Exceptions by using the second argument of {@code perform(callable, handler)}
 * @param <T> The type of the initial input of the pipeline
 * @since 1.0.0
 * @author <a href="https://igventurelli.io">Igor Venturelli</a>
 */
public class Pipe4j<T> {

    private final T value;

    private Pipe4j(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> Pipe4j<T> from(T input) {
        return new Pipe4j<>(input);
    }

    public <U, E extends Throwable> Pipe4j<U> perform(ThrowableFunction<? super T, ? extends U, E> callable) {
        try {
            return Pipe4j.from(callable.apply(value));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public <U, E extends Throwable> Pipe4j<U> perform(ThrowableFunction<? super T, ? extends U, E> callable, Function<? super Throwable, ? extends U> fallback) {
        try {
            return Pipe4j.from(callable.apply(value));
        } catch (Throwable e) {
            return Pipe4j.from(fallback.apply(e));
        }
    }

    public T get() {
        return this.value;
    }
}