package com.example.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
	public String mostrar(Model model) {
		model.addAttribute("productos", productoService.findAll());
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
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		Producto producto= new Producto();
		Optional<Producto> optionalProducto=productoService.get(id);
		producto=optionalProducto.get();
		LOGGER.info("Producto buscado: {}",producto);
		model.addAttribute("producto", producto);
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String update(Producto producto) {
		productoService.actualizar(producto);
		return"redirect:/productos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) {
		productoService.eliminar(id);
		return "redirect:/productos";
	}
}
