package com.assignment.demo.assignment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, String> {

	Optional<UserInfo> findByUsername(String username);
	Optional<UserInfo> findByUsernameAndPassword (String username, String password);
	boolean existsByEmail(String email);

}
