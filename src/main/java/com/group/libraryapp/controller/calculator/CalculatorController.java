package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiRequest;
import org.springframework.web.bind.annotation.*;

//자, 덧셈 API 를 만들어 보자.
@RestController //컨트롤러로 등록시키는 어노테이션 *컨트롤러 : API 진입 지점이다.
public class CalculatorController {
/*
    @GetMapping("/add") //DTO 이것이 Data Transfer Object 이다.
    public int addTwoNum(CalculatorAddRequest request){
       return request.getNum1() + request.getNum2();//이런 식으로 request 객체로 접근하여 바로 반환시킨다.
   }
 */
    @PostMapping("/multi")
    public int multipleTwo(@RequestBody CalculatorMultiRequest request){
        return request.getNumber1() * request.getNumber2();
    }

}
