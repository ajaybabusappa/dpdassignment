package com.assignment.demo.assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyValueRepo extends JpaRepository<KeyValuePair, String> {

}
