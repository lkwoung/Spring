package com.lkwoung.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 웹 어플리케이션의 첫번째 진입점이 controller임
public class HelloController {
    // Static
    @GetMapping("hello") // 웹 어플리케이션에서 /hello에 들어오면 해당하는 메소드가 호출 시켜준다. (get 방식)
    public String hellow(Model model){
        model.addAttribute("data", "hello spring!!" ); //model에 데이터(hello spring!!)를 담고
        return "hello"; // hello.html로 이동
    }

    // MVC
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){ // 외부에서 url 파라미터로 값을 변경
        model.addAttribute("name", name);
        return "hello-template"; // 해당하는 html로 이동.
    }

    // API
    @GetMapping("hello-string")
    @ResponseBody // HTTP 프로토콜에서 BODY 부에 직접 데이터를 직접 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // 해당하는 문자열이 클라이언트에게 그대로 내려감. 뷰 같은게 없음
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); // hello 객체를 만들고
        hello.setName(name); // 웹 브라우저로부터 받은 데이터를 객체에 넣고
        return hello; // 반환한다.
    }

    static class Hello{ //static으로 만들면 클래스 안에서 생성 가능하다
        private String name;

        // 자바빈 표준방식식 getter setter 사용
       public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
