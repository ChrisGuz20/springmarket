package com.example.services;

import java.io.IOException;
import java.nio.file.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;
import java.io.File;

@Service
public class UploadFileService {
	private String folder="images//";
	
	public String saveImage(MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			byte[] bytes=file.getBytes();
			Path path= Paths.get(folder+file.getOriginalFilename());
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
		return "default.jpg";
	}
	
	public void deleteImage(String nombre) {
		String ruta="images//";
		File file=new File(ruta+nombre);
		file.delete();
	}
}