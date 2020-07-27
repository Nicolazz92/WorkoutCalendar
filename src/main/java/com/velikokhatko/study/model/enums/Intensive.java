package com.velikokhatko.study.model.enums;

public enum Intensive {
    LOW(0.5),
    NORMAL(1d),
    HEIGHT(1.5),
    ULTRA(2d);

    private Double multiplex;

    public Double getMultiplex() {
        return multiplex;
    }

    Intensive(Double multiplex) {
        this.multiplex = multiplex;
    }
}
