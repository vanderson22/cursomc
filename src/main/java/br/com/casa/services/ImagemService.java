package br.com.casa.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.casa.exceptions.FileException;

@Service
public class ImagemService {

	public BufferedImage recuperaJPG(MultipartFile mp) {

		try {
			return getJpgFromFile(mp);
		} catch (IOException e) {

			throw new FileException("Erro ao ler o Arquivo");
		}
	}

	/**
	 * Recupera o nome do arquivo de um multipartFile e gera a imagem
	 * 
	 * @throws IOException
	 * 
	 */
	private BufferedImage getJpgFromFile(MultipartFile mp) throws IOException {
		String extension = FilenameUtils.getExtension(mp.getOriginalFilename());
		if (!extension.equals("png") && !extension.equals("jpg"))
			throw new FileException("Somente arquivos JPG ou PNG são supotados - extensão[" + extension + "]");

		BufferedImage image = ImageIO.read(mp.getInputStream());
		if (extension.equals("png"))
			return convertPngToJpg(image);
		return image;

	}

	/* Mais boilerPLateCode */
	public BufferedImage convertPngToJpg(BufferedImage image) {
		BufferedImage bf = new BufferedImage(image.getTileWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		bf.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
		return bf;
	}

	/* Ainda mais boilerPLate */
	public InputStream getInputStream(BufferedImage img, String extension) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ImageIO.write(img, extension, bos);
		} catch (IOException e) {
			throw new FileException("Não foi possível ler o arquivo");
		}
		return new ByteArrayInputStream(bos.toByteArray());
	}

}
