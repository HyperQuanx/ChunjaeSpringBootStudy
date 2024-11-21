package net.fullstack7.springboot.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
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
    List<String> statusList = Arrays.asList("Active", "Inactive", "Pending");

    // Adding attributes to the model
    model.addAttribute("msg", Arrays.toString(arr));
    model.addAttribute("arr", arr);
    model.addAttribute("list", Arrays.asList("AAA", "BBB", "CCC"));
    model.addAttribute("itemId", 12345); // Example itemId for dynamic URL generation
    model.addAttribute("dynamicUrl", "/dynamic/path");
    model.addAttribute("statusList", statusList);

    return "/ex1/ex1"; // Returns the name of the Thymeleaf template to be rendered
  }
}
