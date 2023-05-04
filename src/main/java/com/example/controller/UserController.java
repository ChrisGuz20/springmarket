package com.example.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.slf4j.*;

import com.example.model.*;
import com.example.services.*;

@Controller
@RequestMapping("/usuario")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService usuarioservice;
	
	
	//registro de usuarios
	@GetMapping("/registro")
	public String crearU() {
		return "usuarios/registro";
	}
	
	@PostMapping("/guardar")
	public String guardarU(Usuario usuario) {
		logger.info("Usuario registro: {}", usuario);
		usuario.setTipo("USER");
		usuarioservice.save(usuario);
		return "redirect:/";
	}
}
