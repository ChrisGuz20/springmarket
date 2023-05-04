package com.example.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.*;
import com.example.services.*;
import com.example.model.*;

@Controller
@RequestMapping("/")
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductoServices productoservice;

	@Autowired
	private IUserService usuarioService;

	@Autowired
	private IOrdenService ordenservice;

	@Autowired
	private IDetailOrdenService detalleordenservice;

	// almacenar detalles de orden de compra
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	// datos de la orden de compra
	Orden orden = new Orden();

	@GetMapping("")
	public String inicio(Model model) {
		model.addAttribute("productos", productoservice.findAll());
		return "usuarios/home";
	}

	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id del producto enviado como parametro {}", id);
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoservice.get(id);
		producto = productoOptional.get();
		model.addAttribute("producto", producto);
		return "usuarios/productohome";
	}

	// añadir productos al carrito
	@PostMapping("/cart")
	public String addcarrito(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		DetalleOrden detalleorden = new DetalleOrden();
		Producto producto = new Producto();
		double sumatotal = 0;

		Optional<Producto> optionalproducto = productoservice.get(id);
		log.info("Producto añadido: {}", optionalproducto.get());
		log.info("Cantidad: {}", cantidad);
		producto = optionalproducto.get();

		detalleorden.setCantidad(cantidad);
		detalleorden.setPrecio(producto.getPrecio());
		detalleorden.setNombre(producto.getNombre());
		detalleorden.setTotal(producto.getPrecio() * cantidad);
		detalleorden.setProducto(producto);

		// validar que el producto no se agregue 2 veces al carrito
		Integer idProd = producto.getId();
		boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProd);

		if (!ingresado) {
			detalles.add(detalleorden);
		}

		sumatotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumatotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuarios/carrito";
	}

	// quitar producto del carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteProductocart(@PathVariable Integer id, Model model) {

		// lista nueva de productos
		List<DetalleOrden> ordenesnuevas = new ArrayList<DetalleOrden>();

		for (DetalleOrden detalleorden : detalles) {
			if (detalleorden.getProducto().getId() != id) {
				ordenesnuevas.add(detalleorden);
			}
		}

		// nueva lista de productos restantes
		detalles = ordenesnuevas;

		double sumatotal = 0;
		sumatotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumatotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuarios/carrito";
	}

	// mostrar carrito desde cualquier seccion
	@GetMapping("/getcart")
	public String getcart(Model model) {

		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuarios/carrito";
	}

	// ver la orden de compra
	@GetMapping("/order")
	public String order(Model model) {

		Usuario usuario = usuarioService.findById(1).get();

		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);

		return "/usuarios/resumenorden";
	}

	// guardar la orden
	@GetMapping("/saveorder")
	public String generarOrder() {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenservice.generarnumorden());

		// usuario
		Usuario usuario = usuarioService.findById(1).get();

		orden.setUsuario(usuario);
		ordenservice.save(orden);

		// guardar detalles
		for (DetalleOrden dt : detalles) {
			dt.setOrden(orden);
			detalleordenservice.save(dt);
		}

		// limpiar orden
		orden = new Orden();
		detalles.clear();

		return "redirect:/";
	}

	@PostMapping("/search")
	public String buscarPrd(@RequestParam String nomprod, Model model) {
		log.info("Nombre del producto: {}", nomprod);
		// funcion lamda
		List<Producto> productos = productoservice.findAll().stream().filter(p -> p.getNombre().contains(nomprod))
				.collect(Collectors.toList());
		model.addAttribute("productos",productos);
		return "usuarios/home";
	}

}
