package com.example.controller;

import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
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
	private ProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IOrdenService ordenService;
	
	private Logger logg= org.slf4j.LoggerFactory.getLogger(AdministradorController.class);
	
	@GetMapping("")
	public String home(Model model) {
		List<Producto> productos= productoService.findAll();
		model.addAttribute("productos",productos);
		return "administrador/home";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		return "administrador/usuarios";
	}
	
	@GetMapping("/ordenes")
	public String ordenes(Model model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		model.addAttribute("ordenes", ordenService.findAll());
		return "administrador/ordenes";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model model,@PathVariable Integer id) {
		logg.info("Id de la orden: {}",id);
		Orden orden= ordenService.findById(id).get();
		model.addAttribute("detalles", orden.getDetalle());
		//model.addAttribute("ordenes", ordenService.findAll());
		return "administrador/detalleorden";
	}
}