package com.controllers;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Constants;
import com.Response;
import com.models.Book;
import com.models.BookAccount;
import com.services.BookAccountService;

/**
 * @author RITESH SINGH
 *
 */
@Controller("bookAccountController")
@ComponentScan("com.services")
@RequestMapping(value = "book-account", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookAccountController {

	@Autowired
	@Qualifier("bookAccountService")
	private BookAccountService bookAccountService;
	
	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> saveBookAccount(
			@RequestBody BookAccount bookAccount,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {

		return new ResponseEntity<Response>(
				new Response(200, "BookAccount saved successfully.",bookAccountService.save(bookAccount)),
				HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{bookAccountNo}/status/{status}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> activateBookAccount(
			@PathVariable("bookAccountNo") String bookAccountNo,
			@PathVariable("status") int status,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		return new ResponseEntity<Response>(new Response(200,
				"BookAccount status changed successfully.", status == Constants.BOOK_ACCOUNT_ACTIVATE_STATUS
						? bookAccountService.active(bookAccountNo) : bookAccountService.inActive(bookAccountNo)),
				HttpStatus.OK);
	}
}
