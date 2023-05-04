package com.example.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.services.*;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ProductoServices productoservice;
	
	@GetMapping("")
	public String inicio(Model model) {
		model.addAttribute("productos", productoservice.findAll());
		return "usuarios/home";
	}
}
