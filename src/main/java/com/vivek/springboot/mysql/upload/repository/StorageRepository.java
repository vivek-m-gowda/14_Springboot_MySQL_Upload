package com.vivek.springboot.mysql.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vivek.springboot.mysql.upload.entity.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, String>{

}
