package com.example.services;

import java.util.Optional;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.example.model.Producto;
import com.example.repository.ProductoDAO;

//Metodos crud
@Service
public class ProductoServicesImpl implements ProductoServices{

	@Autowired
	private ProductoDAO productodao;
	
	@Override
	public Producto guardar(Producto producto) {
		return productodao.save(producto);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		return productodao.findById(id);
	}

	@Override
	public void actualizar(Producto producto) {
		productodao.save(producto);
	}

	@Override
	public void eliminar(Integer id) {
		productodao.deleteById(id);
	}

}
