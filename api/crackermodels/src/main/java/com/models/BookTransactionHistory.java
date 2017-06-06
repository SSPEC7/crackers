package com.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.modelUtility.EditableInfo;

/**
 * @author RITESH SINGH
 *
 */
@Document(collection = "bookTransactionHistory")
public class BookTransactionHistory {

	/*
	 * id must be mm/yyyy
	 */
	@Id
	private String id;
	private List<Transaction> transactions;
	private EditableInfo editableInfo;
	
	private class Transaction{
		private String transactionNo;
		private Long quantity;
		
		public String getTransactionNo() {
			return transactionNo;
		}
		public void setTransactionNo(String transactionNo) {
			this.transactionNo = transactionNo;
		}
		public Long getQuantity() {
			return quantity;
		}
		public void setQuantity(Long quantity) {
			this.quantity = quantity;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public EditableInfo getEditableInfo() {
		return editableInfo;
	}

	public void setEditableInfo(EditableInfo editableInfo) {
		this.editableInfo = editableInfo;
	}
	
	
}
