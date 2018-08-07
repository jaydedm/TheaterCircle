package com.example.TheaterCircle.models.dao;

import com.example.TheaterCircle.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;



@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
