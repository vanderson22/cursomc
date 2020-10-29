package br.com.casa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.casa.services.S3Service;

@SpringBootApplication
public class AppCursoMc implements CommandLineRunner {
	@Autowired
	private S3Service s3Service;

//	Executar o aplicativo do springboot
	// Ja vem com um tom cat embedded
	public static void main(String[] args) {
		SpringApplication.run(AppCursoMc.class, args);
	}


//	Método que permite executar  por linha de comando
	@Override
	public void run(String... args) throws Exception {
		s3Service.upload("/Users/vanderson/Downloads/teste.csv");
	}

}
