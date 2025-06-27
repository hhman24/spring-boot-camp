package com.java.spring_boot_camp;

import jakarta.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
//@SpringBootTest
class SpringBootCampApplicationTests {



	@Test
	void hash() throws NoSuchAlgorithmException{
		String password = "123";

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());

		byte[] digest = md.digest();

		String md5Hash = DatatypeConverter.printHexBinary(digest);

		log.info("MD5 round 1: {}", md5Hash);

		md.update(password.getBytes());
		digest = md.digest();

		md5Hash = DatatypeConverter.printHexBinary(digest);

		log.info("MD5 round 2: {}", md5Hash);
	}

}
