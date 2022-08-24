package com.infygo.applicationentity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = CreditCard.CREDITCARD_TABLENAME)
public class CreditCard {
	static final String CREDITCARD_TABLENAME = "CreditCard_Details";

	@Id
	@Column(name="card_number", length=25)
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String cardNumber;
	
	@Column(name="apin", length=25)
	private String apin;
	
	@Column(name="card_holder_name", length=25)
	private String cardHolderName;
	
	@Column(name="cvv", length=3)
	private String cvv;
	
	@Column(name="expiry_month", length=2)
	private String expiryMonth;
	
	@Column(name="expiry_year", length=4)
	private String expiryYear;
	
	@Column(name="total_bill", length=15)
	private String totalBill;
	
	
	
	////Getters and Setters
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getApin() {
		return apin;
	}
	public void setApin(String apin) {
		this.apin = apin;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	public String getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(String totalBill) {
		this.totalBill = totalBill;
	}

	public CreditCard(String cardNumber, String apin, String cardHolderName, String cvv, String expiryMonth,
			String expiryYear, String totalBill) {
		super();
		this.cardNumber = cardNumber;
		this.apin = apin;
		this.cardHolderName = cardHolderName;
		this.cvv = cvv;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.totalBill = totalBill;
	}

	@Override
	public String toString() {
		return "CreditCard [cardNumber=" + cardNumber + ", apin=" + apin + ", cardHolderName=" + cardHolderName
				+ ", cvv=" + cvv + ", expiryMonth=" + expiryMonth + ", expiryYear=" + expiryYear + ", totalBill="
				+ totalBill + "]";
	}
	
public CreditCard assignToCreditCard(CreditCard destination) {
		
		destination.apin = this.apin;
		destination.cardHolderName = this.cardHolderName;
		destination.cardNumber = this.cardNumber;
		destination.cvv = this.cvv;
		destination.expiryMonth = this.expiryMonth;
		destination.expiryYear = this.expiryYear;
		destination.totalBill = this.totalBill;
		
		return destination;
		
	}

public CreditCard() {
	super();
}
	


}
