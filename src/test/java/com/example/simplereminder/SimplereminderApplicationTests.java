package com.example.simplereminder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimplereminderApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void failOnPurpose() {
		// CI가 제대로 동작하는지 보려고 일부러 실패 시키는 테스트
		Assertions.fail("일부러 실패시킨 테스트입니다.");
	}

}
