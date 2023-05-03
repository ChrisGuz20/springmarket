package com.example.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@GetMapping("")
	public String mostrar() {
		return"productos/show";
	}
	
}
