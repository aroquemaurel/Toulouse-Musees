package aderfber.museumtoulouse


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseumController {
    /**
     * Museum service.
     */
    MuseumService museumService

    /**
     * Stars service.
     */
    StarService starService

    /**
     * Methods to trnasfert data.
     */
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * Search museums according filters with <i>max</i> elements per page.
     * @param max Elements max per page
     * @return new view.
     */
    def doResearch(Integer max) {
        String name = params.name
        String postalCode = params.postalCode
        String address = params.address

        params.max = max ?: 5
        params.offset = params.offset ?: 0
        max = params.max
        int offset = params.offset.toInteger();
        int lastElement = max + offset

        List<String> postalCodes = Address.list([sort: "postalCode", order: "asc"]).postalCode.unique();
        List<Museum> museums = museumService.searchMuseums(name, address, postalCode)

        lastElement = lastElement <= museums.size() ? lastElement : museums.size()

        render(view: '/index', model: [museums: museums.subList(offset, lastElement), museumsCount: museums.size(),
                                       postalCodes: postalCodes, params:params,
                                       stars: starService.stars])
    }

    /**
     * Return new view to sort by postal code museums in the list of stars
     * @param max Max elements
     * @return view
     */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        render(view: '/index', model: [stars: starService.stars,
                                       postalCodes: Address.list([sort: "postalCode", order: "asc"]).postalCode.unique()])
    }

    /**
     * Show an Instance of Museum <i>museumInstance</i>
     * @param museumInstance Instance of Museum
     * @return Instance of Museum
     */
    def show() {
        render(view: 'show', model: [stars: starService.stars, museum: Museum.findById(params.museumId as Long),
                                       postalCodes: Address.list([sort: "postalCode", order: "asc"]).postalCode.unique()])

    }

    /**
     * Create a new Museum and return it
     * @return
     */
    def create() {
        respond new Museum(params)
    }


    /**
     *
     */
    protected void notFound() {

        request.withFormat {
            form multipartForm {
                flash.message =
                        message(code: 'default.not.found.message',
                                args: [message(code: 'museum.label',
                                default: 'Museum'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }

    }
}
