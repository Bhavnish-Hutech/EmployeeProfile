package com.emp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.main.model.SubjectProfile;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectProfile, Long>{

}
