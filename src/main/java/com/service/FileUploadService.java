package com.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	public void imageUpload(MultipartFile file, int userId) {

		try {
			String mainPath = "C:\\Users\\SAKSHI\\Documents\\workspace-spring-tool-suite-4-4.15.1.RELEASE\\exptracker\\src\\main\\webapp\\resources\\images";
			File folder = new File(mainPath, userId + "");
			folder.mkdir();// mainpath userid => folder
			File newFile = new File(folder, file.getOriginalFilename());
			byte b[] = file.getBytes();// read all bytes from uploaded file
			FileOutputStream fos = new FileOutputStream(newFile);// fos -> new file
			fos.write(b);// write all uploaded byte into newfile
			fos.close();// save and close
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
