package com.controllers;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.HelperUtility;
import com.Response;
import com.google.gson.Gson;
import com.models.Book;
import com.services.BookService;

/**
 * @author RITESH SINGH
 * @since 2017-06-08
 */
@Controller("bookController")
@ComponentScan("com.services")
@RequestMapping(value = "book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

	@Autowired
	@Qualifier("bookService")
	private BookService bookService;
	
	/**
	 * <b>Create new book.</b>
	 * 
	 * <h3>Request Method POST</h3>
	 * 
	 * @param book : Object Type , param Type RequestBody
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return response 
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> saveBook(
			@RequestBody Book book,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {

		return new ResponseEntity<Response>(
				new Response(200, "Book saved successfully.",bookService.save(book)),
				HttpStatus.OK);
	}
	
	/**
	 * <b>Update the Book.</b>
	 * 
	 * <h3>Request Method PUT</h3>
	 * 
	 * @param book : Object Type , param Type RequestBody
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return response
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> updateBook(
			@RequestBody Book book,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {

		return new ResponseEntity<Response>(
				new Response(200, "Book updates successfully.",bookService.update(book)),
				HttpStatus.OK);
	}
	
	/**
	 * <b>Returns book.</b>
	 *
	 * <h3>Request Method GET</h3>
	 *
	 * @param bookId : String Type, param Type PathVariable
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return response
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> getBookById(
			@PathVariable("bookId") String bookId,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		return new ResponseEntity<Response>(
				new Response(200, "Fetched Book successfully.",bookService.getBookById(bookId)),
				HttpStatus.OK);
	}
	
	/**
	 * <b>Returns all book List.</b>
	 * 
	 * <h3>Request Method GET</h3>
	 * 
	 * Example URL : http://localhost:8989/crackerapi/book/?page=1&size=4&sort=bookName,DESC  
	 * 
	 * @param query : String Type, param Type RequestParam
	 * @param page : Integer Type, param Type RequestParam
	 * @param size : Integer Type, param Type RequestParam
	 * @param sort : String Type, param Type RequestParam
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return response
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> getBooks(
			@RequestParam(value = "q", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		Long totalElements = bookService.count();
		Page<Book> pages = bookService.getBooks(HelperUtility.getPageable(page, size, sort, totalElements));
		
		return new ResponseEntity<Response>(new Response(200, "Fetched books successfully of given page.",
				pages.getContent(), HelperUtility.getPageableResponse(pages)), HttpStatus.OK);
	}
}