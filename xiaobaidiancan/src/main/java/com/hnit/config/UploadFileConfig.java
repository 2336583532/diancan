package com.hnit.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class UploadFileConfig {
	@Value("${file.uploadFolder}")
    private String uploadFolder;

    @Bean
    MultipartConfigElement multipartConfigElement() {
    	MultipartConfigFactory factory = new MultipartConfigFactory();
    	 //单个文件大小200mb
    	factory.setMaxFileSize(DataSize.ofMegabytes(4L));
    	//设置总上传数据大小10GB
    	factory.setMaxRequestSize(DataSize.ofGigabytes(10L));

    	return factory.createMultipartConfig();
    }
}
