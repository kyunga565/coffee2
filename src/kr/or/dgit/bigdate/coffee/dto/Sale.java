package kr.or.dgit.bigdate.coffee.dto;

public class Sale {
	private int rank;
	private Product code;
	private Product title;
	private int price;
	private int amount;
	private int margin;
	
	private int supply_price;
	private int addTax;
	private int sale_price;
	private int margin_price;
	
	
	public Sale(){}
	

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Product getCode() {
		return code;
	}


	public void setCode(Product code) {
		this.code = code;
	}
	
	public Product getTitle() {
		return title;
	}


	public void setTitle(Product title) {
		this.title = title;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getMargin() {
		return margin;
	}


	public void setMargin(int margin) {
		this.margin = margin;
	}


	public int getSupply_price() {
		return supply_price;
	}


	public void setSupply_price(int supply_price) {
		this.supply_price = supply_price;
	}


	public int getAddTax() {
		return addTax;
	}


	public void setAddTax(int addTax) {
		this.addTax = addTax;
	}


	public int getSale_price() {
		return sale_price;
	}


	public void setSale_price(int sale_price) {
		this.sale_price = sale_price;
	}


	public int getMargin_price() {
		return margin_price;
	}


	public void setMargin_price(int margin_price) {
		this.margin_price = margin_price;
	}


	public Sale(int rank, Product code,Product title, int price, int amount, int margin ,int supply_price, int addTax, int sale_price,
			int margin_price) {
		
		this.rank = rank;
		this.code = code;
		this.title = title;
		this.price = price;
		this.amount = amount;
		this.margin = margin;
		
		this.supply_price = supply_price;
		this.addTax = addTax;
		this.sale_price = sale_price;
		this.margin_price = margin_price;
	}


	public Sale(Product code,Product title, int price, int amount, int margin) {
		
		this.code = code;
		this.title = title;
		this.price = price;
		this.amount = amount;
		this.margin = margin;
	}


	public Sale(Product code, int price, int amount, int margin) {
		this.code = code;
		this.price = price;
		this.amount = amount;
		this.margin = margin;
	}


	public String[] toArray() {
		return new String[]{rank+"",code.getCode(),code.getTitle(),price+"",amount+"",margin+"",supply_price+"",addTax+"",sale_price+""
							,margin_price+""};
		
	}

}
