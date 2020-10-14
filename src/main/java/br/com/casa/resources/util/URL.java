package br.com.casa.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static List<Integer> converteLista(String categorias) {

		String[] split = categorias.split(",");

		List<Integer> i = new ArrayList<Integer>();
		for (String s : split) {
			i.add(Integer.parseInt(s));
		}
		return i;
	}

	/**
	 * decodifica par UTF 8
	 * 
	 **/
	public static String decode(String nome) {

		String decode = null;
		try {
			decode = URLDecoder.decode(nome, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decode;
	}

}
