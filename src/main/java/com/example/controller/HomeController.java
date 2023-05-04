package com.example.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import org.slf4j.*;
import com.example.services.*;
import com.example.model.*;


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
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id del producto enviado como parametro {}",id);
		Producto producto =new Producto();
		Optional<Producto> productoOptional=productoservice.get(id);
		producto=productoOptional.get();
		model.addAttribute("producto", producto);
		return "usuarios/productohome";
	}
}
