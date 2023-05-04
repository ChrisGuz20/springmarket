package com.example.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.model.*;
import com.example.repository.*;

@Service
public class DetailOrdenServiceImpl implements IDetailOrdenService{

	@Autowired
	private IDetailsOrdenDAO detalleOrdenRepository;
	
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}

}
