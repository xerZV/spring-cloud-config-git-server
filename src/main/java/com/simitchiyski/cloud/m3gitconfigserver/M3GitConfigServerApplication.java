package com.simitchiyski.cloud.m3gitconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class M3GitConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(M3GitConfigServerApplication.class, args);
	}

}
