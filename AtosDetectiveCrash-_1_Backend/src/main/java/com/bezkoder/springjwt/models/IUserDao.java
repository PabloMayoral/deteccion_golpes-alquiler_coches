package com.bezkoder.springjwt.models;

import org.springframework.data.repository.CrudRepository;


public interface IUserDao extends CrudRepository<User, Long>{

}