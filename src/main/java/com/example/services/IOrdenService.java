package com.example.services;

import java.util.List;

import com.example.model.Orden;

public interface IOrdenService {
	List<Orden> findAll();
	Orden save (Orden orden);
	String generarNumeroOrden();
}