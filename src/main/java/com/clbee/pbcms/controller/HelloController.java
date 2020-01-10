package com.clbee.pbcms.controller;

import com.clbee.pbcms.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    private final PersonService personService;
    public HelloController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/main")
    public String Hello() { // jsp 페이지 호출
        return "main";
    }

    @GetMapping(value = "/hello")
    public String Hello(Model model) { // Spring 에서 제공하는 Model 객체를 사용하여 뷰페이지에 데이터를 넘겨준다.
        model.addAttribute("name" , "TEST");
        System.out.println(personService.selectPersonList().size());
        model.addAttribute("result" , personService.selectPersonList());

        return "hello";
    }
}

