package br.com.casa.exceptions;

/***
 * Apenas para modelar um retorno HTTP
 * 
 */
public class FieldMessage {

	private String fieldName;
	private String msg;

	public FieldMessage(String fieldName, String msg) {
		this.fieldName = fieldName;
		this.msg = msg;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
