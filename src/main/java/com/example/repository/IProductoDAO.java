package com.example.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import com.example.model.Producto;

@Repository
public interface IProductoDAO extends JpaRepository <Producto,Integer>{
	
}
