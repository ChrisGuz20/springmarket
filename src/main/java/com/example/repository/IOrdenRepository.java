package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Orden;
import com.example.model.Usuario;

@Repository
public interface IOrdenRepository  extends JpaRepository<Orden,Integer>{
	List<Orden> findByUsuario (Usuario usuario);
}