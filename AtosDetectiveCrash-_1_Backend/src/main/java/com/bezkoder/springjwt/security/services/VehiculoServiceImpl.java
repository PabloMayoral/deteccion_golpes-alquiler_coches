package com.bezkoder.springjwt.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.security.services.*;
import com.bezkoder.springjwt.models.*;

@Service
public class VehiculoServiceImpl implements IVehiculoService{

	@Autowired
	private IVehiculosDao vehiculoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<vehiculos> findAll() {

		return (List<vehiculos>)vehiculoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public vehiculos findById(Long id) {
		// TODO Auto-generated method stub
		return vehiculoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public vehiculos save(vehiculos vehiculo) {
		// TODO Auto-generated method stub
		return vehiculoDao.save(vehiculo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		vehiculoDao.deleteById(id);
	}

}
