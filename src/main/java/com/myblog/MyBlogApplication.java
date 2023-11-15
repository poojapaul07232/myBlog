package com.myblog;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyBlogApplication{
	public static void main(String[] args)
	{

		SpringApplication.run(MyBlogApplication.class, args);
	}
	@Bean
	//when there is problem while creating a bean usimg third party library  like we use modelmapper  S do not do it it required the details in the main configuration  file  it will help to create the bean .
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}

