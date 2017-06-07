package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.exception.BookException;
import com.modelUtility.EditableInfo;
import com.models.Book;
import com.models.BookAccount;
import com.repositories.BookAccountRepository;
import com.repositories.BookRepository;

/**
 * @author RITESH SINGH
 */
@Service("bookAccountService")
@ComponentScan("com.repositories")
public class BookAccountServiceImpl implements BookAccountService {

	@Autowired
	@Qualifier("bookAccountRepository")
	private BookAccountRepository bookAccountRepository;
	
	@Autowired
	@Qualifier("bookRepository")
	private BookRepository bookRepository;

	@Override
	//@Async
	public BookAccount save(BookAccount bookAccount) {
		
		validate(bookAccount);
		bookAccount = bookAccountRepository.save(bookAccount);
		return bookAccount;
	}
	
	private void validate(BookAccount bookAccount){
		try{
			if(!StringUtils.isEmpty(bookAccount)){
				if(bookAccount.getBookId()!=null){
					Book existingBook = bookRepository.findOne(bookAccount.getBookId());
					if(StringUtils.isEmpty(existingBook)){
						String message = String.format("Book is not exist, So BookAccount can not be created. bookId:"+bookAccount.getBookId());
						throw new BookException(message);
					}
				}
			}
		}catch (Exception e) {
			String message = String.format("Error while validating BookAccount for create.");
			throw new BookException(message, e);
		}
		EditableInfo editableInfo = new EditableInfo();
		editableInfo.setCreatedAt();
		editableInfo.setCreatedBy("Super-Admin");
		editableInfo.setUpdatedAt();
		editableInfo.setUpdatedBy("Super-Admin");
		bookAccount.setEditableInfo(editableInfo);
		bookAccount.setIsActive(false);
	}
	
	@Override
	public Long count() {
		return bookAccountRepository.count();
	}

	@Override
	public List<BookAccount> getBookAccounts() {
		return bookAccountRepository.findAll();
	}

	@Override
	public List<BookAccount> getBookAccounts(Sort sort) {
		return bookAccountRepository.findAll(sort);
	}

	@Override
	public BookAccount getBookAccountById(String bookAccountId) {
		return bookAccountRepository.findOne(bookAccountId);
	}

	@Override
	public BookAccount inActive(String accountNo) {
		BookAccount bookAccount = null;
		try{
			bookAccount = bookAccountRepository.findOne(accountNo);
			if(!StringUtils.isEmpty(bookAccount)){
				bookAccount.setIsActive(false);
				
				EditableInfo editableInfo = bookAccount.getEditableInfo();
				editableInfo.setUpdatedAt();
				editableInfo.setUpdatedBy("Super-Admin");
				bookAccount.setEditableInfo(editableInfo);
				
				bookAccountRepository.save(bookAccount);
			}
		}catch(Exception e){
			String message = String.format("Error while inActivating BookAccount. bookAccountNo is : %s"+accountNo);
			throw new BookException(message, e);
		}
		
		return bookAccount;
	}
	
	@Override
	public BookAccount active(String accountNo) {
		BookAccount bookAccount = null;
		try{
			bookAccount = bookAccountRepository.findOne(accountNo);
			if(!StringUtils.isEmpty(bookAccount)){
				bookAccount.setIsActive(true);
				
				EditableInfo editableInfo = bookAccount.getEditableInfo();
				editableInfo.setUpdatedAt();
				editableInfo.setUpdatedBy("Super-Admin");
				bookAccount.setEditableInfo(editableInfo);
				
				bookAccountRepository.save(bookAccount);
				updateExistingBookWithNewAccountNo(bookAccount);
			}
		}catch(Exception e){
			String message = String.format("Error while inActivating BookAccount. bookAccountNo is : %s"+accountNo);
			throw new BookException(message, e);
		}
		
		return bookAccount;
	}
	
	private void updateExistingBookWithNewAccountNo(BookAccount bookAccount){
		try{
			Book existingBook = bookRepository.findOne(bookAccount.getBookId());
			Book.Account account = existingBook.getAccount();
			account.setAccountNo(bookAccount.getAccountNo());
			account.setIsDiscount(bookAccount.getIsDiscount());
			account.setMrp(bookAccount.getMrp());
			account.setQuantity(bookAccount.getQuantity());
			account.setUnit(bookAccount.getUnit());
			existingBook.setAccount(account);
			
			if(bookAccount.getIsDiscount())
				existingBook.setIsOffer(true);
			else
				existingBook.setIsOffer(false);
			
			if(bookAccount.getQuantity()>0)
				existingBook.setIsAvailable(true);
			else
				existingBook.setIsAvailable(false);
			
			
			EditableInfo editableInfo = existingBook.getEditableInfo();
			editableInfo.setUpdatedAt();
			editableInfo.setUpdatedBy("Super-Admin");
			existingBook.setEditableInfo(editableInfo);
			
			bookRepository.save(existingBook);
		}catch(Exception e){
			bookAccountRepository.delete(bookAccount);
			String message = String.format("Error while Updating Book at the time of BookAccount create.");
			throw new BookException(message, e);
		}
	}
}