package com.example.demo;

import com.example.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by OskarPraca on 2017-06-03.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByRole(String role);
    List<User> findByDatetimeBetween(Date date1, Date date2);
    List<User> findByUsernameContainingAndIdGreaterThan(String reg, int id);
    Page<User> findAll(Pageable pageable);
}
