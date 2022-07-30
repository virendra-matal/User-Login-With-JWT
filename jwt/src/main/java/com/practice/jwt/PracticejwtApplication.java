package com.practice.jwt;

import com.practice.jwt.Repository.UserRepo;
import com.practice.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;


@SpringBootApplication
public class PracticejwtApplication implements CommandLineRunner {
	@Autowired
	private UserRepo userRepo;

	static Random random=new Random();


	public void CreateUser(){
		int id=random.nextInt(100);
		User user=new User();
		user.setUsername("User"+id);
		user.setId(id);
		user.setEmail("User"+id+"@gamil.com");
		user.setEnable(true);
		user.setPassword("User"+id);
		user.setRole("Admin");
		this.userRepo.save(user);
	}

	public static void main(String[] args) {
		SpringApplication.run(PracticejwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CreateUser();

	}


}
