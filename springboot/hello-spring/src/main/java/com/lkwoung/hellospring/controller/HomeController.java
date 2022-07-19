package com.lkwoung.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 스프링이 먼저 controller에 등록된 루트도메인이 있는지 찾아봄.
// 매핑되는 것이 있으면 호출
public class HomeController {
    @GetMapping("/") //domain, localhost에 들어오자마자 home.html 출력
    public String home(){
        return "home"; // home.html로 이동 정적의 index는 무시됨
    }
}
