package com.lazarev.springbootlesson2.model;

public class EmployeeAggregate {
    private String field;
    private double value;

    public EmployeeAggregate(String field, double value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
