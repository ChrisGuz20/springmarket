package com.example.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.example.model.*;

@Repository
public interface IOrdenDAO  extends JpaRepository<Orden,Integer>{
	

}
