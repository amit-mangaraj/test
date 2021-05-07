package com.ltts.logexport.fileproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("file:E:\\myProperties.properties")
public class Properties {

	
	@Value("${p.zipFilepath}")
	private String zipFilepath;
	
	@Value("${p.logFilepath}")
	private String FOLDER;

	public String getZipFilepath() {
		return zipFilepath;
	}

	public String getFOLDER() {
		return FOLDER;
	}

	public void setFOLDER(String fOLDER) {
		FOLDER = fOLDER;
	}

	public void setZipFilepath(String zipFilepath) {
		this.zipFilepath = zipFilepath;
	}

	 
} 
