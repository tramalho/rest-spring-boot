package com.tramalho.rest.spring.boot

import com.tramalho.rest.spring.boot.person.controller.PersonController
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RestSpringBootApplicationTests {

	@Autowired
	private lateinit var personController: PersonController

	@Test
	fun contextLoads() {
		assertNotNull(personController)
	}

}
