package com.pluralsight.javamultithreading;

public interface TaskWorker {
    void setTarget(Object obj);
    void doWork();
}
