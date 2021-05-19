package com.notice;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import lombok.extern.slf4j.Slf4j;

@EnableJpaAuditing
@SpringBootApplication
@Slf4j
public class NoticeApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HhjApplication.class, args);
		SpringApplication application = new SpringApplicationBuilder()
				.sources(NoticeApplication.class)
				.listeners(new ApplicationPidFileWriter("./this.pid"))
				.build();
		application.run(args);
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
	
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(NoticeApplication.class);
//	}
	
	@PreDestroy
	public void destroy() {
		log.error("shutdown appl");
	}
}
