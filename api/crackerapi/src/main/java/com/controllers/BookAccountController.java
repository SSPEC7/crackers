package com.controllers;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

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
import com.models.BookAccount;
import com.services.BookAccountService;

/**
 * @author RITESH SINGH
 * @since 2017-06-08
 * @version	1.8
 *
 */
@Controller("bookAccountController")
@ComponentScan("com.services")
@RequestMapping(value = "book-account", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookAccountController {

	@Autowired
	@Qualifier("bookAccountService")
	private BookAccountService bookAccountService;
	
	/**
	 * <b>Create a new bookAccount for a book but bookAccount will be created in
	 * deactivate mode.</b>
	 * 
	 * <h3>Request Method POST<h3>
	 * 
	 * @param book : Object Type , param Type RequestBody
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return response
	 * @throws UnknownHostException
	 */
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
	
	/**
	 * <b>Activate or Deactivate the bookAccount<b>
	 *
	 * <h3>Request Method POST</h3>
	 *
	 * @param bookAccountNo : String Type, param Type PathVariable
	 * @param status : Boolean Type, param Type PathVariable
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return response
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/{bookAccountNo}/status/{status}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> activateInactiveBookAccount(
			@PathVariable("bookAccountNo") String bookAccountNo,
			@PathVariable("status") Boolean status,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		return new ResponseEntity<Response>(
				new Response(200, "BookAccount status changed successfully.",
						status ? bookAccountService.active(bookAccountNo) : bookAccountService.inActive(bookAccountNo)),
				HttpStatus.OK);
	}
	
	/**
	 * <b>Returns all bookAccounts List.</b>
	 * 
	 * <h3>Request Method GET</h3>
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
	@RequestMapping(value = "/", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> getBookAccountsPageable(
			@RequestParam(value = "q", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		Long totalElements = bookAccountService.count();
		Page<BookAccount> pages = bookAccountService.getBookAccounts(HelperUtility.getPageable(page, size, sort, totalElements));
		
		return new ResponseEntity<Response>(new Response(200, "Fetched BookAccounts successfully.",
				pages.getContent(), HelperUtility.getPageableResponse(pages)), HttpStatus.OK);
	}
	
	/**
	 * <b>Returns bookAccounts List of given status and status is true for active
	 * bookAccount otherwise false.</b>
	 *
	 * <h3>Request Method GET</h3>
	 *
	 * @param status : Boolean Type, param Type PathVariable
	 * @param page : Integer Type, param Type RequestParam
	 * @param size : Integer Type, param Type RequestParam
	 * @param sort : String Type, param Type RequestParam
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return response
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/status/{status}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> getBookAccountsByStatus(
			@PathVariable("status") Boolean status,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		List<BookAccount> list = bookAccountService.getBookAccountByStatus(status);
		return new ResponseEntity<Response>(new Response(200, "Fetched BookAccount successfully.", list,
				HelperUtility.setTotalElements(list, new HashMap<String, Object>())), HttpStatus.OK);
	}
	
	/**
	 * <b>Returns bookAccount of given bookAccountNo.</b>
	 *
	 * <h3>Request Method GET</h3>
	 *
	 * @param bookAccountNo : String Type, param Type PathVariable
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return response
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/{bookAccountNo}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> getBookAccountByAccountNo(
			@PathVariable("bookAccountNo") String bookAccountNo,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		return new ResponseEntity<Response>(new Response(200,
				"Fetched BookAccount successfully.", bookAccountService.getBookAccountById(bookAccountNo)),
				HttpStatus.OK);
	}
	
	/**
	 * <b>Returns all bookAccounts List of book.</b>
	 *
	 * <h3>Request Method GET</h3>
	 *
	 * @param bookId : String Type, param Type PathVariable
	 * @param page : Integer Type, param Type RequestParam
	 * @param size : Integer Type, param Type RequestParam
	 * @param sort : String Type, param Type RequestParam
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return response
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> getBookAccountByBookId(
			@PathVariable("bookId") String bookId,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		List<BookAccount> list = bookAccountService.getBookAccountByBookId(bookId);
		return new ResponseEntity<Response>(new Response(200, "Fetched BookAccounts of given bookId successfully.",
				list, HelperUtility.setTotalElements(list, new HashMap<String, Object>())), HttpStatus.OK);
	}
	
	/**
	 * <b>Returns bookAccounts List of given bookId and bookAccount status and list
	 * counts If you wants to fetch all those active/inActive bookAccounts of
	 * book then use it. If you are requested for active bookAccount of a book
	 * then only one bookAccount will be return in List.</b>
	 * 
	 * <h3>Request Method GET</h3>
	 * 
	 * @param bookId : String Type, param Type PathVariable
	 * @param status : Boolean Type, param Type PathVariable
	 * @param page : Integer Type, param Type RequestParam
	 * @param size : Integer Type, param Type RequestParam
	 * @param sort : String Type, param Type RequestParam
	 * @param accessToken : String Type but not required, param Type RequestHeader
	 * @param response
	 * @return response
	 * @throws UnknownHostException
	 */
	@CrossOrigin
	@RequestMapping(value = "/{bookId}/account-status/{status}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response> getBookAccountByBookId(
			@PathVariable("bookId") String bookId,
			@PathVariable("status") Boolean status,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestHeader(value = "X-AUTH-HEADER", defaultValue = "foo") String accessToken,
			HttpServletResponse response)throws UnknownHostException {
		
		List<BookAccount> list = bookAccountService.getBookAccountsByBookIdAndStatus(bookId,status);
		return new ResponseEntity<Response>(
				new Response(200, "Fetched BookAccounts of given BookId and Bookaccount Status successfully.",
						list,HelperUtility.setTotalElements(list, new HashMap<String,Object>())),
				HttpStatus.OK);
	}
}
