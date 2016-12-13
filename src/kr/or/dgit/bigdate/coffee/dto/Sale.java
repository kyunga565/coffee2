package kr.or.dgit.bigdate.coffee.dto;

public class Sale {
	private Product code;
	
	private int price;
	private int amount;
	private int profit;
	
	public Sale(){}

	public Sale(Product code, int price, int amount, int profit) {
		this.code = code;
	
		this.price = price;
		this.amount = amount;
		this.profit = profit;
	}

	public Product getCode() {
		return code;
	}

	public void setCode(Product code) {
		this.code = code;
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

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s", code, price, amount,
				profit);
	}

	public String[] toArray() {
		return new String[]{code.getCode(),code.getTitle(),price+"",amount+"",profit+""};
		
	}

}
