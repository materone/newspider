package com.chufan.news.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(2)
public class CmdRunner2 implements CommandLineRunner{
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Init in cmd runner 2");
	}

}
