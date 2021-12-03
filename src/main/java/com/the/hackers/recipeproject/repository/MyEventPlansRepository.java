package com.the.hackers.recipeproject.repository;

import com.the.hackers.recipeproject.data.transfer.object.MyEventPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyEventPlansRepository extends JpaRepository<MyEventPlans , Integer> {

}
