package com.example.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.model.*;
import com.example.repository.*;

@Service
public class OrdenServiceImpl implements IOrdenService{

	@Autowired
	private IOrdenDAO ordenRepository;
	
	@Override
	public Orden save(Orden orden) {
		return ordenRepository.save(orden);
	}

}
