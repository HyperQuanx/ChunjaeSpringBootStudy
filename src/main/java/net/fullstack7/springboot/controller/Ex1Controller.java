package net.fullstack7.springboot.controller;

import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/ex1")
public class Ex1Controller {
  @GetMapping("/ex1")
  public String ex1(Model model) {
    String[] arr = new String[] {"AAA", "BBB", "CCC"};

    model.addAttribute("msg", "gd");
    model.addAttribute("arr", arr);
    model.addAttribute("list", Arrays.asList("AAA", "BBB", "CCC"));
    return "/ex1/ex1";
  }
}
