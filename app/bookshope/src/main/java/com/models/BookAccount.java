package com.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.modelUtility.EditableInfo;

/**
 * @author RITESH SINGH
 *
 */
@Document(collection = "bookAccount")
public class BookAccount {

	@Id
	private String accountNo;
	private String bookId;
	private Double mrp;
	private Boolean isDiscount;
	private Long discount;
	private String discountRules;
	private Long fromDate;
	private Long toDate;
	private Boolean isActive;
	private Long quantity;
	private String unit;
	private EditableInfo editableInfo;
	
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	public Boolean getIsDiscount() {
		return isDiscount;
	}
	public void setIsDiscount(Boolean isDiscount) {
		this.isDiscount = isDiscount;
	}
	public Long getDiscount() {
		return discount;
	}
	public void setDiscount(Long discount) {
		this.discount = discount;
	}
	public String getDiscountRules() {
		return discountRules;
	}
	public void setDiscountRules(String discountRules) {
		this.discountRules = discountRules;
	}
	public Long getFromDate() {
		return fromDate;
	}
	public void setFromDate(Long fromDate) {
		this.fromDate = fromDate;
	}
	public Long getToDate() {
		return toDate;
	}
	public void setToDate(Long toDate) {
		this.toDate = toDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public EditableInfo getEditableInfo() {
		return editableInfo;
	}
	public void setEditableInfo(EditableInfo editableInfo) {
		this.editableInfo = editableInfo;
	}
}
