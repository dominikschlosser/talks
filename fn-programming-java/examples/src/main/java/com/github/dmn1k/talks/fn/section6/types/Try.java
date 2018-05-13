package com.github.dmn1k.talks.fn.section6.types;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.*;

public abstract class Try<T> {

    protected Try() {
    }

    public static <U> Try<U> ofFailable(Supplier<U> f) {
        Objects.requireNonNull(f);

        try {
            return Try.successful(f.get());
        } catch (Throwable t) {
            return Try.failure(t);
        }
    }

    public static <U> Try<U> failure(Throwable e) {
        return new Failure<>(e);
    }

    public static <U> Try<U> successful(U x) {
        return new Success<>(x);
    }
    

    public abstract <U> Try<U> map(Function<? super T, ? extends U> f);
    public abstract <U> Try<U> flatMap(Function<? super T, Try<U>> f);
    public abstract T recover(Function<? super Throwable, T> f);
    public abstract Try<T> recoverWith(Function<? super Throwable, Try<T>> f);
    public abstract T orElse(T value);
    public abstract Try<T> orElseTry(Supplier<T> f);
    public abstract <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;
    public abstract T get() throws Throwable;
    public abstract T getUnchecked();

    public abstract boolean isSuccess();


    public abstract Try<T> filter(Predicate<T> pred);
    public abstract Optional<T> toOptional();
}

class Success<T> extends Try<T> {
    private final T value;

    public Success(T value) {
        this.value = value;
    }

    @Override
    public <U> Try<U> flatMap(Function<? super T, Try<U>> f) {
        Objects.requireNonNull(f);
        try {
            return f.apply(value);
        } catch (Throwable t) {
            return Try.failure(t);
        }
    }

    @Override
    public T recover(Function<? super Throwable, T> f) {
        Objects.requireNonNull(f);
        return value;
    }

    @Override
    public Try<T> recoverWith(Function<? super Throwable, Try<T>> f) {
        Objects.requireNonNull(f);
        return this;
    }

    @Override
    public T orElse(T value) {
        return this.value;
    }

    @Override
    public Try<T> orElseTry(Supplier<T> f) {
        Objects.requireNonNull(f);
        return this;
    }

    @Override
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return value;
    }

    @Override
    public T get() throws Throwable {
        return value;
    }

    @Override
    public T getUnchecked() {
        return value;
    }

    @Override
    public <U> Try<U> map(Function<? super T, ? extends U> f) {
        Objects.requireNonNull(f);
        try {
            return new Success<>(f.apply(value));
        } catch (Throwable t) {
            return Try.failure(t);
        }
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public Try<T> filter(Predicate<T> p) {
        Objects.requireNonNull(p);

        if (p.test(value)) {
            return this;
        } else {
            return Try.failure(new NoSuchElementException("Predicate does not match for " + value));
        }
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.ofNullable(value);
    }
}


class Failure<T> extends Try<T> {
    private final Throwable e;

    Failure(Throwable e) {
        this.e = e;
    }

    @Override
    public <U> Try<U> map(Function<? super T, ? extends U> f) {
        Objects.requireNonNull(f);
        return Try.failure(e);
    }

    @Override
    public <U> Try<U> flatMap(Function<? super T, Try<U>> f) {
        Objects.requireNonNull(f);
        return Try.failure(e);
    }

    @Override
    public T recover(Function<? super Throwable, T> f) {
        Objects.requireNonNull(f);
        return f.apply(e);
    }

    @Override
    public Try<T> recoverWith(Function<? super Throwable, Try<T>> f) {
        Objects.requireNonNull(f);
        try{
            return f.apply(e);
        }catch(Throwable t){
            return Try.failure(t);
        }
    }

    @Override
    public T orElse(T value) {
        return value;
    }

    @Override
    public Try<T> orElseTry(Supplier<T> f) {
        Objects.requireNonNull(f);
        return Try.ofFailable(f);
    }

    @Override
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        throw exceptionSupplier.get();
    }

    @Override
    public T get() throws Throwable {
        throw e;
    }

    @Override
    public T getUnchecked() {
        throw new RuntimeException(e);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }
    @Override
    public Try<T> filter(Predicate<T> pred) {
        return this;
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.empty();
    }
}
