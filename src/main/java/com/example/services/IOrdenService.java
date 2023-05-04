package com.example.services;

import java.util.*;

import com.example.model.*;

public interface IOrdenService {
	List<Orden> findAll();
	
	Orden save (Orden orden);
}
