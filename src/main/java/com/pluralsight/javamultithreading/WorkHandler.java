package com.pluralsight.javamultithreading;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface WorkHandler {
    boolean useThreadPool();
}
