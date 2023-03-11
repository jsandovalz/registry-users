package com.nisum.registryusers;

import com.nisum.registryusers.models.entity.User;
import com.nisum.registryusers.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RegistryUsersApplicationTests {

	@Autowired
	private IUserService _userService;

	@Test
	void saveUser(){
		User user = new User();
		user.setName("Jose");
		user.setEmail("jsandovalz@gmail.com");
		user.setPassword("1Ab$57gg");
		User userCreated = _userService.save(user);
		assertThat(userCreated.getToken()).isNotNull();
		assertThat(userCreated.getLastLogin()).isNotNull();
		assertThat(userCreated.getCreatedDate()).isBefore(LocalDateTime.now());
		assertThat(userCreated.isActive()).isTrue();
	}


}
