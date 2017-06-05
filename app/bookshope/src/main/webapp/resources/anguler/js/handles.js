$(document).ready(function(){
	
	// checked or unchecked all handles for posts
	$(document).on('click','#allHandles',function(event) {
		var status = $("#allHandles").is(":checked") ? true : false;
	    $(".handles").prop("checked",status);
    });
	
	$(document).on('click','.handles',function(event) {
		var flag = true;
	    $('.handles').each(function() {
            if(!($(this).is(":checked"))){
            	$("#allHandles").prop("checked",false);
            	flag = false;
            }
            if(flag)
            	$("#allHandles").prop("checked",true);
        });
    });
	
});