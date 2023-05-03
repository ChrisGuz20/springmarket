package com.example.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import com.example.model.Producto;

@Repository
public interface ProductoDAO extends JpaRepository <Producto,Integer>{
	
}
