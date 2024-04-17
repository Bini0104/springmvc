package com.ohgiraffers.thymeleaf.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @Configuration를 상속받음 스캔을 했을 때 다른 폴더에 있으면 인식을 못해서 오류가 난다.
@SpringBootApplication
public class Chap01ThymeleafLectureSourceApplication {

	public static void main(String[] args) {

		SpringApplication.run(Chap01ThymeleafLectureSourceApplication.class, args);

	}
}
