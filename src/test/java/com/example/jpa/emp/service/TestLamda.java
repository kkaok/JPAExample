package com.example.jpa.emp.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TestLamda {

    public static void main(String[] args) {

        // test data 
        List<Integer> numbers = Arrays.asList(1,5,4,2,3);
        
        // 레거시 형태 출력 
        for(Integer i : numbers) System.out.println(i);
        
        // stream 이용한 출력 
        numbers.forEach(System.out::println);
        
        // 인자가 하나인 형태 
        numbers.forEach(n -> System.out.println(n));
        
        // 인자가 하나인 형태 
        numbers.forEach((n) -> { System.out.println(n); } );

        // stream에서 함수 형태 처리  
        Consumer<Integer> method = n -> System.out.println(n);
        numbers.forEach(method);
    }
        
}
