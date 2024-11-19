package net.fullstack7.springboot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/hello")
public class HelloController {
  @GetMapping("/hello")
  public String hello(Model model) {
    model.addAttribute("msg", "Hello World");
    return "hello";
  }
}