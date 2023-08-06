package com.assignment.demo.assignment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KVDao {
	@Autowired
	KeyValueRepo repo;
	public boolean addpair (KeyValuePair kvp) {
		if (repo.existsById(kvp.getKey())) return false;
		repo.save(kvp);
		return true;
	}
	
	public Optional<KeyValuePair> getPair(String id) {
		return repo.findById(id);
	}
	
	public boolean deletePair (String id) {
		KeyValuePair kvp = repo.findById(id).orElse(null);
		if (kvp == null) return false;
		else {
			repo.deleteById(id);
		}
		return true;
	}
	
	public boolean update (String id, String value) {
		KeyValuePair kvp = repo.findById(id).orElse(null);
		if (kvp == null) return false;
		else {
			kvp.setValue(value);
			repo.save(kvp);
		}
		return true;
	}
}
