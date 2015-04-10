class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        name index: "/"(view: "/index")
        name about: "/a-propos"(view: "/about")
        "500"(view:'/error')
	}
}
