package com.example.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import com.example.model.*;

@Repository
public interface IUserDAO extends JpaRepository<Usuario,Integer>{
	Optional<Usuario> findByEmail(String email);
}
