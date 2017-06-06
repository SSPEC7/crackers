package com.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.modelUtility.EditableInfo;

/**
 * @author RITESH SINGH
 *
 */
@Document(collection = "books")
public class Book implements Serializable {

	private static final long serialVersionUID = 7746218756589213487L;
	
	@Id
	private String id;
	private String bookName;
	private String bookCode;
	private String writterName;
	private String publisherName;
	private String series;
	private String version;
	private Long totalPages;
	private String aboutBook;
	private String category;
	private String type;
	private String bookImage;
	private String bookCoverImage;
	private Boolean isAvailable;
	private Boolean isOffer;
	private String tag;
	private Account account;
	
	public Boolean getIsOffer() {
		return isOffer;
	}

	public void setIsOffer(Boolean isOffer) {
		this.isOffer = isOffer;
	}

	private EditableInfo editableInfo;
	
	public class Account{
		private String accountNo;
		private Long quantity;
		private String unit;
		private Boolean isDiscount;
		private Double mrp;
		
		public String getAccountNo() {
			return accountNo;
		}
		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}
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
		
		public Boolean getIsDiscount() {
			return isDiscount;
		}
		public void setIsDiscount(Boolean isDiscount) {
			this.isDiscount = isDiscount;
		}
		public Double getMrp() {
			return mrp;
		}
		public void setMrp(Double mrp) {
			this.mrp = mrp;
		}
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getWritterName() {
		return writterName;
	}

	public void setWritterName(String writterName) {
		this.writterName = writterName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public String getAboutBook() {
		return aboutBook;
	}

	public void setAboutBook(String aboutBook) {
		this.aboutBook = aboutBook;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	public String getBookCoverImage() {
		return bookCoverImage;
	}

	public void setBookCoverImage(String bookCoverImage) {
		this.bookCoverImage = bookCoverImage;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public EditableInfo getEditableInfo() {
		return editableInfo;
	}

	public void setEditableInfo(EditableInfo editableInfo) {
		this.editableInfo = editableInfo;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
}
