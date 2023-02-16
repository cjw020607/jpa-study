package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        //data인 키에 hello!!!값을 전달해줌
        model.addAttribute("data","hello!!!");
        //spring boot가 자동으로 hello.html해줌
        return "hello";
    }
}
