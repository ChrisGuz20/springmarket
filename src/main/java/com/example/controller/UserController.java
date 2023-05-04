package com.example.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.servlet.http.HttpSession;

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
	
	@GetMapping("/login")
	public String login() {
		return "usuarios/login";
	}
	
	@PostMapping("/access")
	public String acceder(Usuario usuario, HttpSession session) {
		logger.info("Accesos: {}",usuario);
		Optional<Usuario> user=usuarioservice.findByEmail(usuario.getEmail());
		//logger.info("Usuario de db: {}", user.get());
		if(user.isPresent()) {
			session.setAttribute("iduser", user.get().getId());
			if(user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			}else {
				return "redirect:/";
			}
		}else {
			logger.info("Usuario no existente");
		}
		
		return "redirect:/";
	}
}
