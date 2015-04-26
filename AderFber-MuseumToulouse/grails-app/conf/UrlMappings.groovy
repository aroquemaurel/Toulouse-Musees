class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"museum", action: "index")
        name about: "/a-propos"(view: "/about")
        "500"(view:'/error')
	}
}
