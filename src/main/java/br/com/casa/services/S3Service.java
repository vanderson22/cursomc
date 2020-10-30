package br.com.casa.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

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
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public URI upload(MultipartFile localFilePath) {

		String originalFilename = localFilePath.getOriginalFilename();
		InputStream is = null;
		try {
			is = localFilePath.getInputStream();
		} catch (IOException e) {
			log.error("Ocorreu um erro ao recuperar o arquivo " + e.getStackTrace());
			throw new RuntimeException("Ocorreu um erro ao recuperar o arquivo");
		}
		String contentType = localFilePath.getContentType();

		try {
			return upload(is, originalFilename, contentType);
		} catch (URISyntaxException e) {
			log.error("Ocorreu um erro ao recuperar a URI " + e.getStackTrace());
			throw new RuntimeException("Ocorreu um erro ao recuperar a URI");
		}

	}

	public URI upload(InputStream is, String localFilePath, String contentType) throws URISyntaxException {

		log.info("Iniciando Upload do arquivo " + localFilePath);
		
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType(contentType);
		
		s3Client.putObject(bucketName, localFilePath, is, meta);
		log.info("Finalizado Upload do arquivo " + localFilePath);

		return s3Client.getUrl(bucketName, localFilePath).toURI();

	}
}
