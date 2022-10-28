package com.vivek.springboot.mysql.upload.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import com.vivek.springboot.mysql.upload.entity.Storage;

public interface StorageService {
	
	public void uploadToLocal(MultipartFile file);
	
	public Storage uploadToDb(MultipartFile file);
	
	public Storage downloadFile (String fileId) throws NotFoundException;
	
//	public Storage deleteFile(String fileId);

}
