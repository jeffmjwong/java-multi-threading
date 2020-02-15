package com.pluralsight.javamultithreading;

import java.lang.annotation.Retention;

@Retention()
public @interface WorkHandler {
    boolean useThreadPool();
}
