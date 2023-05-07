package com.example.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.example.model.Producto;
import com.example.repository.IProductoRepository;

//Metodos crud
@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private IProductoRepository productoRepository;
	
	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		return productoRepository.findById(id);
	}

	@Override
	public void update(Producto producto) {
		productoRepository.save(producto);
	}

	@Override
	public void delete(Integer id) {
		productoRepository.deleteById(id);
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}
	
	

}