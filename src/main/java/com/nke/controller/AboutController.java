package com.nke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {

@RequestMapping("/about")
	public String welcome(Model model) {

		return "about";
	}
}