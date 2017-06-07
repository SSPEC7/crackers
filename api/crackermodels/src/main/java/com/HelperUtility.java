package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class HelperUtility {

	public static Pageable getPageable(int page, int size, String sort){
		List<Sort.Order> orders = new ArrayList<>();
        if(sort!=null){
        	String[] propOrderSplit = sort.split(",");
            String property = propOrderSplit[0];
            if (propOrderSplit.length == 1) {
                orders.add(new Sort.Order(property));
            } else {
                Sort.Direction direction
                        = Sort.Direction.fromStringOrNull(propOrderSplit[1]);
                orders.add(new Sort.Order(direction, property));
            }
        }
        return new PageRequest(page, size,
                orders.isEmpty() ? null : new Sort(orders));
	}
	
	public static <T> Map<String,Object> getPageableResponse(Page<T> page){
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("totalPages", page.getTotalPages());
		data.put("totalElements", page.getTotalElements());
		data.put("nextPageable", page.nextPageable());
		data.put("previousPageable", page.previousPageable());
		data.put("sort", page.getSort());
		data.put("currentPageNumber", (page.getNumber()+1));
		
		return data;
	}
	
	public static <T> void setTotalElements(List<T> list,Map<String,Object> data){
		if(data!=null)
			data.put("totalElements", list!=null ? list.size() : 0);
	}
}
