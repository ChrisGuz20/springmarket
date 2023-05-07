package com.example.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Producto;
import com.example.model.Usuario;
import com.example.services.IUsuarioService;
import com.example.services.ProductoService;
import com.example.services.UploadFileService;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.slf4j.*;


@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	Integer Idusuarios;
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private UploadFileService upload;
	
	
	@GetMapping("")
	public String mostrar(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return"productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		return"productos/create";
	}
	
	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		Idusuarios= (Integer) session.getAttribute("idusuario");
		
		LOGGER.info("Este es el objeto producto {}",producto);
		//Usuario u= usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString() )).get();
		//Usuario u= new Usuario (1,"","","","","","","");
		Usuario u= usuarioService.findById(Idusuarios).get();
		producto.setUsuario(u);
		
		//imagen
		if(producto.getId()==null) {//Cuando se crea un producto
			String nombreImagen=upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}else {
			
		}
		
		productoService.save(producto);
		return"redirect:/productos";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto= new Producto();
		Optional<Producto> optionalProducto=productoService.get(id);
		producto=optionalProducto.get();
		LOGGER.info("Producto buscado: {}",producto);
		model.addAttribute("producto", producto);
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException  {
		Producto prod= new Producto();
		prod=productoService.get(producto.getId()).get();
		
		if(file.isEmpty()) { //cuando editamos el producto pero no se cambia la imagen
			
			producto.setImagen(prod.getImagen());
		}else { //editamos el producto y se cambia la imagen		
			//eliminar cuando no sea la imagen predeterminada
			if(!prod.getImagen().equals("default.jpg")) {
				upload.deleteImage(prod.getImagen());
			}
			
			String nombreImagen=upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		producto.setUsuario(prod.getUsuario());
		productoService.update(producto);
		return"redirect:/productos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		Producto prod=new Producto();
		prod=productoService.get(id).get();
		
		//eliminar cuando no sea la imagen predeterminada
		if(!prod.getImagen().equals("default.jpg")) {
			upload.deleteImage(prod.getImagen());
		}
		productoService.delete(id);
		return "redirect:/productos";
	}
}