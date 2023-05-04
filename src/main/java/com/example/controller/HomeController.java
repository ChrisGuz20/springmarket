package com.example.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.*;
import com.example.services.*;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger log=LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProductoServices productoservice;
	
	@GetMapping("")
	public String inicio(Model model) {
		model.addAttribute("productos", productoservice.findAll());
		return "usuarios/home";
	}
	
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id) {
		log.info("Id del producto enviado como parametro {}",id);
		
		return "usuarios/productohome";
	}
}
