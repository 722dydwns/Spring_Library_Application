package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//자, 덧셈 API 를 만들어 보자.
@RestController //컨트롤러로 등록시키는 어노테이션 *컨트롤러 : API 진입 지점이다.
public class CalculatorController {

    @GetMapping("/add") //API 명세에 따라 Http 메소드는 GET, HTTP path = "/add" 인 API 등록
   public int addTwoNumbers(@RequestParam int num1, @RequestParam int num2){
       return num1 + num2;
   }
   @GetMapping("/add") //DTO 이것이 Data Transfer Object 이다.
    public int addTwoNum(CalculatorAddRequest request){
       return request.getNum1() + request.getNum2();//이런 식으로 request 객체로 접근하여 바로 반환시킨다.
   }

}
