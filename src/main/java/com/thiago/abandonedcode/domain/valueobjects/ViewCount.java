package com.thiago.abandonedcode.domain.valueobjects;

public record ViewCount(Long value) {

    public static ViewCount zero() {
        return new ViewCount(0L);
    }

    public ViewCount increment() {
        return new ViewCount(this.value + 1);
    }
}
