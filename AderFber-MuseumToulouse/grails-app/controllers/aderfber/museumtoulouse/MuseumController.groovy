package aderfber.museumtoulouse


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseumController {

    MuseumService museumService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def doResearch() {
        String name = params.name
        String postalCode = params.codePostal
        String address = params.address

        List<Museum> museums = museumService.searchMuseums(name, address, postalCode)
        render(view: '/index', model: [museums: museums])
    }
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Museum.list(params), model: [museumInstanceCount: Museum.count()]
    }

    def show(Museum museumInstance) {
        respond museumInstance
    }

    def create() {
        respond new Museum(params)
    }

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

    def edit(Museum museumInstance) {
        respond museumInstance
    }

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
