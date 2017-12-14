package com.nalbam.common.util;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

public class FutureUtil {

    public static <T> CompletableFuture<T> toCF(final ListenableFuture<T> lf) {
        final CompletableFuture<T> cf = new CompletableFuture<T>();
        lf.addCallback(cf::complete, cf::completeExceptionally);
        return cf;
    }

}
