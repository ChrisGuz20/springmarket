package com.example.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.slf4j.*;
import com.example.services.*;
import com.example.model.*;


@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger log=LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProductoServices productoservice;
	
	//almacenar detalles de orden de compra
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
	
	//datos de la orden de compra
	Orden orden= new Orden();
	
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
	
	@PostMapping("/cart")
	public String addcarrito(@RequestParam Integer id, @RequestParam Integer cantidad,Model model) {
		DetalleOrden detalleorden = new DetalleOrden();
		Producto producto = new Producto();
		double sumatotal = 0;
		
		Optional<Producto> optionalproducto = productoservice.get(id);
		log.info("Producto añadido: {}", optionalproducto.get());
		log.info("Cantidad: {}",cantidad);
		producto=optionalproducto.get();
		
		detalleorden.setCantidad(cantidad);
		detalleorden.setPrecio(producto.getPrecio());
		detalleorden.setNombre(producto.getNombre());
		detalleorden.setTotal(producto.getPrecio()*cantidad);
		detalleorden.setProducto(producto);
		
		detalles.add(detalleorden);
		
		sumatotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		
		orden.setTotal(sumatotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		return "usuarios/carrito";
	}
}
