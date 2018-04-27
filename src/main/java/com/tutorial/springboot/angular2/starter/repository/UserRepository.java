package com.tutorial.springboot.angular2.starter.repository;

import com.tutorial.springboot.angular2.starter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.username = ?1 and u.password=?2 ")
	User findByUsernameAndPassword(String name,String address);
}
