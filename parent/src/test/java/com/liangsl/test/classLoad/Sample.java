package com.liangsl.test.classLoad;

public class Sample {
    private Sample instance;

    public void setSample(Object instance) {
        this.instance = (Sample) instance;
    }
}
