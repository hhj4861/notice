package com.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notice.model.entity.FileEntity;



@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {	
		
	
}
