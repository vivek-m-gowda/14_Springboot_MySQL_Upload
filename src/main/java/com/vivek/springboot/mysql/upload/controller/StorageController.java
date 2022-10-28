package com.vivek.springboot.mysql.upload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vivek.springboot.mysql.upload.entity.Storage;
import com.vivek.springboot.mysql.upload.response.StorageResponse;
import com.vivek.springboot.mysql.upload.service.StorageService;

@RestController
@RequestMapping("/api/v1")
public class StorageController {

	@Autowired
	private StorageService storageService;
	
	
	@PostMapping("/upload/local")
	public void uploadToLocal(@RequestParam("file") MultipartFile multipartFile) {
		storageService.uploadToLocal(multipartFile);
	}

	@PostMapping("/upload/db")
	public StorageResponse uploadToDb(@RequestParam("file") MultipartFile multipartFile) {
		
		Storage fileUpload = storageService.uploadToDb(multipartFile);
		StorageResponse response = new StorageResponse();
		if(fileUpload != null) {
			String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/api/v1/download/")
					.path(fileUpload.getFileId())
					.toUriString();
			response.setDownloadUrl(downloadUri);
			response.setFileId(fileUpload.getFileId());
			response.setFileType(fileUpload.getFileType());
			response.setMessage("File uploaded Sucessfully");
			response.setUplodedStatus(true);
			return response;
		}
		response.setMessage("Oops Somethinf went wrong, please re-upload the file");
		return response;
		
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String id) throws NotFoundException {
		Storage data = storageService.downloadFile(id);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(data.getFileType()))
				.header("Content-disposition", "attachment; filename=\"" + data.getFileName() + "\"")
				.body(new ByteArrayResource(data.getFileData()));
	}
}
