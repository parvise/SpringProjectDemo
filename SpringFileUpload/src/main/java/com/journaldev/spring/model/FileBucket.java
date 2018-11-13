package com.journaldev.spring.model;

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {
	MultipartFile file;

	String description;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "FileBucket [file=" + file + ", description=" + description + "]";
	}

}
