/* 
===========================================
#  auther: RITESH SINGH                   #
#  User App ViewConfig DataSet            #
#                                         #
===========================================
*/ 

var templateUrlConfig = {
	appTemplateEntryPoint : "/empApp/",
	userTemplateGroup : "/users/"
};

var userViewsConfig = {
		indexView : {
			header: viewHeaderFooter.header,
	  		content:{
    	      template: 'launchUser',
				controller: 'NewUserCtrl'
    	     },
    	     footer : viewHeaderFooter.footer
		},
		homeView : {
			header: viewHeaderFooter.header,
			content:{
		      templateUrl: templateUrlConfig.appTemplateEntryPoint
						+ templateUrlConfig.userTemplateGroup
						+ 'launchUser',
				controller: 'NewUserCtrl'
		     },
		     footer : viewHeaderFooter.footer
		}
	};