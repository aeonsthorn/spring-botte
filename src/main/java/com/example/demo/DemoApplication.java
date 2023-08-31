package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public List<User> users = new ArrayList<User>();

    public static void main(String[] args) {
      SpringApplication.run(DemoApplication.class, args);
    }

   @PostMapping("/users")
   public User createUser(@RequestBody CreateUserDto dto){

	User user = new User(this.users.size() +1, dto.getName());

	this.users.add(user);

	return user;
	}

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(value = "name", defaultValue = "World") String name) {


	

	return this.users;
    }

    @GetMapping("/sum")
    public SumResult getSum(@RequestParam(value= "x") int x, 
				@RequestParam(value="y") int y) {

			return new SumResult(x + y);

	}
}

