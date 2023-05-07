package com.example.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import com.example.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository <Producto,Integer>{
	
}