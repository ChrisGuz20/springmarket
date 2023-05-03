package com.example.services;

import java.util.*;
import com.example.model.Producto;

public interface ProductoServices {
	public Producto guardar(Producto producto);
	public Optional<Producto> get(Integer id);
	public void actualizar(Producto producto);
	public void eliminar(Integer id);
	public List<Producto> findAll();
}
