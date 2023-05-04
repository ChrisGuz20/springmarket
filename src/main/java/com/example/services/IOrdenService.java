package com.example.services;

import java.util.*;

import com.example.model.*;
import com.example.services.*;


public interface IOrdenService {
	List<Orden> findAll();
	
	Orden save (Orden orden);
	String generarnumorden();
}
