package kr.or.dgit.bigdate.coffee.dto;

public class Product {
	private String code;
	private String title;
	
	public Product(){}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Product(String code, String title) {
		this.code = code;
		this.title = title;
	}

	@Override
	public String toString() {
		return String.format("%s (%s)", code, title);
	}

	public String[] toArray() {
		return new String[]{code,title};
		
	}
}
