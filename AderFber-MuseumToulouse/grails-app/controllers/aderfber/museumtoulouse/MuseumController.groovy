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
     *
     * @param max
     * @return
     */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        render(view: '/index', model: [stars: starService.stars,
                                       postalCodes: Address.list([sort: "postalCode", order: "asc"]).postalCode.unique()])
    }

    /**
     *
     * @param museumInstance
     * @return
     */
    def show(Museum museumInstance) {
        respond museumInstance
    }

    /**
     *
     * @return
     */
    def create() {
        respond new Museum(params)
    }

    /**
     *
     * @param museumInstance
     * @return
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
     *
     * @param museumInstance
     * @return
     */
    def edit(Museum museumInstance) {
        respond museumInstance
    }

    /**
     *
     * @param museumInstance
     * @return
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
     *
     * @param museumInstance
     * @return
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
