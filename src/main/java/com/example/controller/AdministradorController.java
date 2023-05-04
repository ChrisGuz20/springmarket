package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.model.*;
import com.example.services.*;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	
	@Autowired
	private ProductoServices productoservice;
	
	@GetMapping("")
	public String home(Model model) {
		List<Producto> productos= productoservice.findAll();
		model.addAttribute("productos",productos);
		return "administrador/home";
	}
}
