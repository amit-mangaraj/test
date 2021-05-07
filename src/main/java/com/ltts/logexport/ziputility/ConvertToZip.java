package com.ltts.logexport.ziputility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.ltts.logexport.fileproperties.*;
@Component
public class ConvertToZip {

	
	@Autowired
	Properties myProperties1;
	
	  
	  
	  public void zip_one_file(){
	    
	    try (FileOutputStream fos = new FileOutputStream(myProperties1.getFOLDER().concat(".zip"));
	        ZipOutputStream zos = new ZipOutputStream(fos)) {            
	      Path sourcePath = Paths.get(myProperties1.getFOLDER());
	     
	      Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>(){
	        @Override
	        public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
	         
	          if(!sourcePath.equals(dir)){
	          
	            zos.putNextEntry(new ZipEntry(sourcePath.relativize(dir).toString() + "/"));            
	            zos.closeEntry();    
	          }
	          return FileVisitResult.CONTINUE;
	        }
	        @Override
	        public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
	          zos.putNextEntry(new ZipEntry(sourcePath.relativize(file).toString()));
	          Files.copy(file, zos);
	          zos.closeEntry();
	          return FileVisitResult.CONTINUE;
	        }
	      });
	    } catch (IOException e) {
	    
	      e.printStackTrace();
	    }
	  }
}
	