package com.vivek.springboot.mysql.upload.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class MultipartExceptionHandler {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String storageException(MaxUploadSizeExceededException exception, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return "File size limit exceeded";
	}

}
