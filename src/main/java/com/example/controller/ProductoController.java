package com.example.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.slf4j.*;
import com.example.model.*;
import com.example.services.*;


@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoServices productoService;
	
	
	@GetMapping("")
	public String mostrar() {
		return"productos/show";
	}
	
	@GetMapping("/create")
	public String crear() {
		return"productos/create";
	}
	
	@PostMapping("/save")
	public String guardar(Producto producto) {
		LOGGER.info("Este es el objeto producto {}",producto);
		Usuario u = new Usuario(1,"","","","","","","");
		producto.setUsuario(u);
		productoService.guardar(producto);
		return"redirect:/productos";
	}
	
}
