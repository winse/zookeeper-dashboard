class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/main"(view:"/index")
		"/"(controller:"ZKSource")
		"500"(view:'/error')
		"/error"(view:'/error')
	}
}
