package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/result")
public class ResultController {

  @GetMapping()
  public String resultView(ModelMap model, @ModelAttribute ("error")String error) {
    if(error.length() == 0) {
      model.addAttribute("changeSuccess", true);
    } else {
      model.addAttribute("errorString", error);
    }
    return "result";
  }
}
