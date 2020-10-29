package br.com.casa.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	private Logger log = LoggerFactory.getLogger(S3Service.class);
	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	/**
	 * 
	 * Realiza o upload do arquivo para o Amazon S3
	 */
	public void upload(String localFilePath) {

		try {
			File file = new File(localFilePath);
			log.info("Iniciando Upload do arquivo " + localFilePath);
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "teste.csv", file);
			s3Client.putObject(putObjectRequest);
			log.info("Finalizado Upload do arquivo " + localFilePath);
		} catch (AmazonServiceException e) {
			log.error(
					"Ocorreu Erro ao enviar o arquivo  MSG [" + e.getMessage() + "] - Code [" + e.getErrorCode() + "]");
		} catch (AmazonClientException e) {
			log.error("Ocorreu Erro ao enviar o arquivo  MSG [" + e.getMessage() + "] - Cause [" + e.getCause() + "]");
		}

	}
}
