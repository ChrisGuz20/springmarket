package com.example.services;

import java.util.List;

import com.example.model.Orden;
import com.example.model.Usuario;

public interface IOrdenService {
	List<Orden> findAll();
	Orden save (Orden orden);
	String generarNumeroOrden();
	List<Orden> findByUsuario (Usuario usuario);
}