var accessToken = "";
function statusChangeCallback(response) {
	console.log('statusChangeCallback');
	console.log(response);
	console.log(response.authResponse.accessToken);
	
	
	if (response.status === 'connected') {
		accessToken = response.authResponse.accessToken;
		testAPI();
	} else if (response.status === "disconnected") {
		alert("disc");
	}else if (response.status === 'not_authorized') {

		document.getElementById('status').innerHTML = 'Please log '
				+ 'into this app.';
	} else {

		document.getElementById('status').innerHTML = 'Please log '
				+ 'into Facebook.';
	}
}

function checkLoginState() {
	FB.getLoginStatus(function(response) {
		
		if(response.status != 'connected'){
			response.status = "disconnected";
			logOff();
		}
		console.log(response);
		statusChangeCallback(response);
	});
}

window.fbAsyncInit = function() {
	FB.init({
		/* must one thing care about that in development of your facebook-app only app's owner can login 
		if you want to login every one the must be to live your facebook-app using facebook-canvas */
		appId : '1131060046929336',
		cookie : true,

		xfbml : true,
		version : 'v2.7'
	});

	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
};

(function(d, s, id) {

	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function testAPI() {
	console.log('Welcome!  Fetching your information.... ');
	FB.api('/me', function(response) {
		console.log('Successful login for: ' + JSON.stringify(response));

		var myJson = {
			'id' : response.id,
			'name' : response.name,
			'gender' : response.gender,
			'emailId' : response.email,
			'accessToken' : accessToken,
			'socialType' : 'fb',
			'userName' : userName
		};

		/*alert(JSON.stringify(myJson));*/
		
		var request = $.ajax({
			url : "/trend-app/admin/google/social-login",
			type : "POST",
			data : JSON.stringify(myJson),
			dataType : "json",
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			}
		});

		request.done(function(msg) {
			//alert(JSON.stringify(msg.gender));
			$("#ficn").hide();
			$("#fbtn").show();
		});

		request.fail(function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		});

		document.getElementById('status').innerHTML = 'Thanks for logging in, '
				+ response.name + '!';
	}, {
		fields : 'id,name,email,gender'
	});
}

function logOff(){
	
	$("#ficn").show();
	$("#fbtn").hide();
}

function facebookGetPostsAPI() {
	
	var myJson = {
		'userName' : userName
	};
	/*alert(JSON.stringify(myJson));*/
		
	var request = $.ajax({
		url : "/trend-app/admin/facebook/get-posts",
		type : "POST",
		data : JSON.stringify(myJson),
		dataType : "json",
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		}
	});

	request.done(function(msg) {
		
		var dataHtml = '';
		var attachments = '';
		var count = 1;
		$.each( msg.data, function( key, obj ) {
			
			attachments = '';
			if(obj.sourceType == "photo" || obj.sourceType == "link"){
				attachments ='<img style="width:400; height:315;" src="'+obj.imageUrl+'" />';
			}
			
			if(obj.sourceType == "video"){
				attachments = '<img id="'+count+'playImg" style="width:400;  height:315;" src="'+obj.imageUrl+'" />'
							+'<button id="'+count+'playBtn" class="fPlay"><i class="fa fa-play"></i></button>'
					       +'<iframe id="'+count+'playSc" style="display:none;" width="400" height="315" src="'+obj.sourceUrl+'" frameborder="0" allowfullscreen></iframe>';
			}
			
			  dataHtml += '<div class="row">'
					  		+'<div class="col-md-6 col-lg-6">'
					  			+'<div id="'+count+'flip" class="flip alert alert-success">'
					  				+'<div class="container-fluid">'
					  					+'<div class="alert-icon">'
					  						+'<i class="material-icons" style="color:blue;">speaker_notes</i>'
					  					+'</div>'		
					  					+'<b>'+obj.createdAt+'</b>'
					  				+'</div>'
					  			+'</div>'  
					  			+'<div id="'+count+'panal" style="display: none;" class="alert alert-dafault">'
					  				+'<div class="container-fluid">'
					  					+'<div class="card card-stats">'
											+'<div class="card-header" style="background-color:#354c8c;color:#FEFEFE;">'
												+'<i class="fa fa-facebook"></i>'
											+'</div>'
											+'<div class="card-content">'
												+'<p class="category">Likes</p>'
												+'<h3 class="title">+245</h3>'
											+'</div>'
											+'<div style="font-weight : 400; margin-left:20px; position:relative; margin-bottom:20px; font-size: small;">'+obj.message+'</div>'
											+'<div style="font-weight : 400; margin-left:20px; position:relative; margin-bottom:20px; font-size: medium;">'+obj.story+'</div>'
											+'<div class="video-wrapper" style="margin-left:20px;">'+attachments+'</div>'
											+'<div class="card-footer">'
												+'<div class="stats">'
													+'<div class="material-icons">thumb_up</div>'+obj.totalLikes
												+'</div>'
												+'<div class="stats">'
													+'<i class="material-icons">update</i> '+obj.createdAt
												+'</div>'
											+'</div>'
										+'</div>'
					  				+'</div>'
					  			+'</div>'
					  		+'</div>'
					  	+'</div>';
			  
			  count++;
			});
			$("#fbData").html(dataHtml);
		
	});

	request.fail(function(jqXHR, textStatus) {
		alert("Request failed: " + textStatus);
	});
}


$(document).ready(function(){
	// Javascript method's body can be found in assets/js/demos.js
	demo.initDashboardPageCharts();
	$("#fbtn").hide();
	$("#fbtn").click(function(){
	    alert("The paragraph was clicked."+userName);
	    facebookGetPostsAPI();
	});
	
	$(document).on('click','.flip',function(event) {
		var panal = ((this.id).substring(0,(this.id).indexOf("flip"))) + "panal";
		$("#"+panal).slideToggle("slow");
    });
	
	$(document).on('click','.fPlay',function(event) {
		var playVcd = ((this.id).substring(0,(this.id).indexOf("playBtn"))) + "playSc";
		var playImg = ((this.id).substring(0,(this.id).indexOf("playBtn"))) + "playImg";
		$("#"+playVcd).show();
		$("#"+playImg).hide();
		$("#"+this.id).hide();
    });
});

/*================================================Google Login Js=============================================== */

var auth2;
var myProfileJson;
// 
var SCOPES = ['https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/gmail.readonly'];

function onSuccess(googleUser) {

	var profile = googleUser.getBasicProfile();
	gapi.client.load('plus', 'v1', function() {
		var request = gapi.client.plus.people.get({
			'userId' : 'me'
		});

		request.execute(function(resp) {
			alert(JSON.stringify(resp));
			myProfileJson = {
				'id' : resp.id,
				'name' : resp.displayName,
				'gender' : resp.gender,
				'emailId' : resp.emails[0].value
			};
		});
	});

	auth2.grantOfflineAccess().then(loaddata);
}

function loaddata(authResult) {
	alert("hello " + authResult['code']);

	var myJson = {
		'accessToken' : authResult['code'],
		'id' : myProfileJson.id,
		'name' : myProfileJson.name,
		'gender' : myProfileJson.gender,
		'emailId' : myProfileJson.emailId
	};

	var request = $.ajax({
		url : "/trend-app/admin/google/social-google-login",
		type : "POST",
		data : JSON.stringify(myJson),
		dataType : "json",
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		}
	});

	request.done(function(msg) {
				alert(JSON.stringify(msg));
				var profileHTML = '<a class="btn btn-sm" href="javascript:void(0);" onclick="signOut();">Sign out</a>';
				$('.userContent').html(profileHTML);
				$('#gSignIn').slideUp('slow');
			});

	request.fail(function(jqXHR, textStatus) {
		alert("Request failed: " + textStatus);
	});
}

function onFailure(error) {
	alert(error);
}

function renderButton() {

	gapi.load('auth2',function() {
		auth2 = gapi.auth2.init({
			client_id : '706307070913-4dgls2uncjsl2soj778e1hvj2arh9tgm.apps.googleusercontent.com',
			// Scopes to request in addition to 'profile' and 'email'
			//scope: 'additional_scope'
			'scope': SCOPES.join(' '),
	        'immediate': true
			});
	});

	gapi.signin2.render('gSignIn', {
		'scope' : 'profile email',
		'width' : 80,
		'height' : 25,
		'longtitle' : false,
		'theme' : 'dark',
		'onsuccess' : onSuccess,
		'onfailure' : onFailure
	});
}

function signOut() {
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function() {
		$('.userContent').html('');
		$('#gSignIn').slideDown('slow');
	});
}