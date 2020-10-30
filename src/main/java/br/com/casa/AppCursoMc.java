package br.com.casa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppCursoMc implements CommandLineRunner {

//	Executar o aplicativo do springboot
	// Ja vem com um tom cat embedded
	public static void main(String[] args) {
		SpringApplication.run(AppCursoMc.class, args);
	}

//	Método que permite executar  por linha de comando
	@Override
	public void run(String... args) throws Exception {
	}

}
