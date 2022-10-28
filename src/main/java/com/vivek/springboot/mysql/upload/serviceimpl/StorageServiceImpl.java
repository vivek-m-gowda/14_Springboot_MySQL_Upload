package com.vivek.springboot.mysql.upload.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vivek.springboot.mysql.upload.entity.Storage;
import com.vivek.springboot.mysql.upload.repository.StorageRepository;
import com.vivek.springboot.mysql.upload.service.StorageService;

@Service 
public class StorageServiceImpl implements StorageService {

	private String uploadFolderPath = "/home/dell/Desktop/Upload_";
	
	@Autowired
	private StorageRepository storageRepository;

	@Override
	public void uploadToLocal(MultipartFile file) {
		
		try {
			byte[] data = file.getBytes();
			Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
			Files.write(path, data);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Storage uploadToDb(MultipartFile file) {
		
		Storage fileUpload = new Storage();
		try {
			fileUpload.setFileData(file.getBytes());
			fileUpload.setFileType(file.getContentType());
			fileUpload.setFileName(file.getOriginalFilename());
			return storageRepository.save(fileUpload);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Storage downloadFile(String fileId) throws NotFoundException {
		Optional<Storage> optionalId = storageRepository.findById(fileId);
		if (!optionalId.isEmpty()) {
			return optionalId.get();
		}else {
			throw new NotFoundException();
		}
	}

//	@Override
//	public Storage deleteFile(String fileId) {
//		Optional<Storage> optionalId = storageRepository.findById(fileId);
//		if (!optionalId.isEmpty()) {
//			return optionalId.delete();
//		}else {
//			throw new NotFoundException();
//		}
//		
//	}
	
	

}
