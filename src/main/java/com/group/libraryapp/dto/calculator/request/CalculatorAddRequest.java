package com.group.libraryapp.dto.calculator.request;

import com.group.libraryapp.controller.calculator.CalculatorController;

public class CalculatorAddRequest { //객체 단위로 쿼리 받기
    private final int num1;
    private final int num2;

    public CalculatorAddRequest(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    //get, set 함수
    public int getNum1() {
        return num1;
    }
    public int getNum2() {
        return num2;
    }
}