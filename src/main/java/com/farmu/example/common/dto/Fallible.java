package com.farmu.example.common.dto;

public interface Fallible {

    boolean success();

    String errorMessage();

}