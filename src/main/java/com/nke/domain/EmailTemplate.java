package com.nke.domain;

import java.io.Serializable;

public class EmailTemplate implements Serializable{
	
	private static final long serialVersionUID = 82334723729994043L;
	
	private String emailSubject;
	private String emailMessage;
	private String customerPhone;
	private String customerEmail;
	private String customerName;
	private String customerInquiry;
	private String recipientEmail;
	
	public EmailTemplate() {
		super();
	}
	
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailMessage() {
		
		return emailMessage;
	}
	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}
	
	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerInquiry() {
		return customerInquiry;
	}

	public void setCustomerInquiry(String customerInquiry) {
		this.customerInquiry = customerInquiry;
	}
	
	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	
	
}
