package com.example.services;

import java.util.*;

import com.example.model.*;

public interface IUserService {
	Optional<Usuario> findById(Integer id);
	
}
