package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.repository.*;
import com.example.model.*;

@Service
public class UserServicesImpl  implements IUserService{

	@Autowired
	private IUserDAO userdao;

	@Override
	public Optional<Usuario> findById(Integer id) {
		return userdao.findById(id);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return userdao.save(usuario);
	}


}
