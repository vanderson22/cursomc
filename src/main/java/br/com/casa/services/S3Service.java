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

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.casa.exceptions.FileException;

@Service
public class S3Service {

	private static final Logger log = LoggerFactory.getLogger(S3Service.class);
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
	public URI upload(MultipartFile multipartFile) {

		String originalFilename = multipartFile.getOriginalFilename();
		InputStream is = null;
		try {
			is = multipartFile.getInputStream();
		} catch (IOException e) {
			log.error("Ocorreu um erro ao recuperar o arquivo " + e.getStackTrace());
			throw new FileException("Ocorreu um erro ao recuperar o arquivo");
		}
		String contentType = multipartFile.getContentType();
		return upload(is, originalFilename, contentType);

	}

	public URI upload(InputStream is, String originalName, String contentType) {

		try {
			log.info("Iniciando Upload do arquivo " + originalName);

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);

			s3Client.putObject(bucketName, originalName, is, meta);
			log.info("Finalizado Upload do arquivo " + originalName);

			URI uri = s3Client.getUrl(bucketName, originalName).toURI();
			log.info(uri.toString());
			return uri;
		} catch (URISyntaxException e) {
			log.error(e.getStackTrace().toString());
			throw new RuntimeException("Erro na sintaxe da URI " + e.getMessage());

		} catch (AmazonS3Exception e) {
			throw new AmazonServiceException(e.getMessage(), e);
		}
	}
}
