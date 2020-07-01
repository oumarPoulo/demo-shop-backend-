package com.oumarpoulo.shopapp.core.usecase;

public interface UseCase<I, O> {
    O execute(I input);
}
