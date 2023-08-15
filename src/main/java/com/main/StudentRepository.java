package com.main;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.main.*;


@Repository
public interface StudentRepository  extends JpaRepository<Student, Long>, CrudRepository<Student, Long>{

	List<Student> findByUserid(String userid);
	List<Student> findByid(long id); 
}
