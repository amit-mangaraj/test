package com.ltts.logexport.filestorageservice;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltts.logexport.fileproperties.Properties;
import com.ltts.logexport.ziputility.ConvertToZip;
@Component
public class FileStorageService {
	@Autowired
	Properties myProperties;
	
	@Autowired
	private ConvertToZip cz;
	
	public void saveFile(HttpServletResponse res) throws IOException, Exception {
		
		cz.zip_one_file();
		File file = new File(myProperties.getZipFilepath());
		res.setContentType("application/octet-stream");
		String headerKey ="Content-Disposition";
		String headerValue="attachment;filename="+file.getName();
		res.setHeader(headerKey, headerValue);
		ServletOutputStream outputStream = res.getOutputStream();
		BufferedInputStream inputStream =new BufferedInputStream(new FileInputStream(file));
		byte[] buffer = new byte[8192];
		int bytesRead=-1;
		while((bytesRead=inputStream.read(buffer))!=-1)
		{
			outputStream.write(buffer,0,bytesRead);
			
		}
		inputStream.close();
		outputStream.close();
	

	}

}
