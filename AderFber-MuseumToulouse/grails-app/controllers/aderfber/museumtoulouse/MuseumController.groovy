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
                                       stars: starService.stars,
                                      successToStars: params.successToStars,
                                       successUnstars: params.successUnstars])
    }

    /**
     * Search a Museum by Id.
     * @return new veiw of the museum
     */
    def doRsearchById() {
        Museum m = Museum.findById(params['museumId'] as Long)
        museumService.searchMuseums(
                m.name.toString(),
                m.address.street,
                m.address.postalCode)
        render(view: '/index', model: [museums: [m], museumsCount: 1,
                                       postalCodes: m.address.postalCode,
                                       params:params,
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
     * Save in database the Museum <i>museumInstance</i>
     * @param museumInstance Instance of Museum
     * @return Instance of Museum
     */
    @Transactional
    def save(Museum museumInstance) {
        if (museumInstance == null) {
            notFound()
            return
        }

        if (museumInstance.hasErrors()) {
            respond museumInstance.errors, view: 'create'
            return
        }

        //Address address = Address.get(museumInstance.address.id)
        //Manager manager = Manager.get(museumInstance.manager.id)
        museumService.insertOrUpdateMuseum(
                museumInstance,
                museumInstance.address,
                museumInstance.manager)
        //museumInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message',
                                    args: [message(code: 'museum.label',
                                    default: 'Museum'), museumInstance.id])
                redirect museumInstance
            }
            '*' { respond museumInstance, [status: CREATED] }
        }
    }

    /**
     * Change the current Museum by the new <i>museumInstance</i>
     * @param museumInstance
     * @return Instance of Museum
     */
    def edit(Museum museumInstance) {
        respond museumInstance
    }

    /**
     * Update Instance of Museum <i>museumInstance</i>
     * @param museumInstance Instance of Museum
     * @return Museum Instance
     */
    @Transactional
    def update(Museum museumInstance) {
        if (museumInstance == null) {
            notFound()
            return
        }

        if (museumInstance.hasErrors()) {
            respond museumInstance.errors, view: 'edit'
            return
        }
        Address address = Address.get(museumInstance.address.id)
        Manager manager = Manager.get(museumInstance.manager.id)
        museumService.insertOrUpdateMuseum(museumInstance,address,manager)
        //museumInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message =
                        message(code: 'default.updated.message',
                                args: [message(code: 'Museum.label',
                                default: 'Museum'), museumInstance.id])
                redirect museumInstance
            }
            '*' { respond museumInstance, [status: OK] }
        }

    }

    /**
     * Remove the museum <i>museumInstance</i>
     * @param museumInstance Instance of Museum
     * @return Instance of Museum
     */
    @Transactional
    def delete(Museum museumInstance) {

        if (museumInstance == null) {
            notFound()
            return
        }

        museumService.deleteMuseum(museumInstance)
        //museumInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message =
                        message(code: 'default.deleted.message',
                                args: [message(code: 'Museum.label',
                                default: 'Museum'), museumInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }

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
