package com.vivek.springboot.mysql.upload.response;

import lombok.Data;

@Data
public class StorageResponse {

	private String fileId;
	private String fileType;
	private String message;
	private boolean uplodedStatus;
	private String downloadUrl;
}
