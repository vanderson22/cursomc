package br.com.casa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Realiza a configuração do repositorio de fotos
 * 
 */
@Configuration
public class S3Config {

	@Value("${aws.secret_access_key_id}")
	private String secretKey;

	@Value("${aws.access_key_id}")
	private String accessKey;
	
	@Value("${s3.regiao}")
	private String region;

	/*
	 * Boiler plate code, retorna as credenciais e região informados no arquivo de
	 * properties
	 */
	@Bean
	public AmazonS3 s3Client() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		return AmazonS3ClientBuilder
								.standard()
								.withRegion(Regions.fromName(region))
								.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
								.build();

	}
}
