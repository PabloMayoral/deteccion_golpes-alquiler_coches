package com.bezkoder.springjwt.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.Alquiler;
import com.bezkoder.springjwt.models.IAlquileresDao;

@Service
public class AlquilerServiceImpl implements IAlquilerService{

		
	@Autowired
	private IAlquileresDao alquilerDao;
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Alquiler> findAll() {
		return (List<Alquiler>) alquilerDao.findAll();
	}


	@Override
	@Transactional(readOnly=true)
	public Alquiler findById(Long id) {
		return alquilerDao.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public Alquiler save(Alquiler alquiler) {
		return alquilerDao.save(alquiler);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		alquilerDao.deleteById(id);
		
	}

}
