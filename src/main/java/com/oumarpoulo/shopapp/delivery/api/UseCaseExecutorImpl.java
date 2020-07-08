package com.oumarpoulo.shopapp.delivery.api;

import com.oumarpoulo.shopapp.core.usecase.UseCase;
import com.oumarpoulo.shopapp.core.usecase.UseCaseExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class UseCaseExecutorImpl implements UseCaseExecutor {
    @Override
    public <RX, I, O> CompletableFuture<RX> execute(UseCase<I, O> useCase, I input, Function<O, RX> outputMapper) {
        return CompletableFuture
                .supplyAsync(() -> input)
                .thenApplyAsync(useCase::execute)
                .thenApplyAsync(outputMapper);
    }
}
