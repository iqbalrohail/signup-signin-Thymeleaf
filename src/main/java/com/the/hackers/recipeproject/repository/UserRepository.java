package com.the.hackers.recipeproject.repository;

import com.the.hackers.recipeproject.data.transfer.object.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
