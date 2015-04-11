package aderfber.museumtoulouse


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AskingVisitController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AskingVisit.list(params), model: [askingVisitInstanceCount: AskingVisit.count()]
    }

    def show(AskingVisit askingVisitInstance) {
        respond askingVisitInstance
    }

    def create() {
        respond new AskingVisit(params)
    }

    @Transactional
    def save(AskingVisit askingVisitInstance) {
        if (askingVisitInstance == null) {
            notFound()
            return
        }

        if (askingVisitInstance.hasErrors()) {
            respond askingVisitInstance.errors, view: 'create'
            return
        }

        askingVisitInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message =
                        message(
                                code: 'default.created.message',
                                args: [message(code: 'askingVisit.label',
                                        default: 'AskingVisit'),
                                       askingVisitInstance.id])
                redirect askingVisitInstance
            }
            '*' { respond askingVisitInstance, [status: CREATED] }
        }
    }

    def edit(AskingVisit askingVisitInstance) {
        respond askingVisitInstance
    }

    @Transactional
    def update(AskingVisit askingVisitInstance) {
        if (askingVisitInstance == null) {
            notFound()
            return
        }

        if (askingVisitInstance.hasErrors()) {
            respond askingVisitInstance.errors, view: 'edit'
            return
        }

        askingVisitInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message =
                        message(
                                code: 'default.updated.message',
                                args: [message(code: 'AskingVisit.label',
                                        default: 'AskingVisit'),
                                       askingVisitInstance.id])
                redirect askingVisitInstance
            }
            '*' { respond askingVisitInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AskingVisit askingVisitInstance) {

        if (askingVisitInstance == null) {
            notFound()
            return
        }

        askingVisitInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message =
                        message(
                                code: 'default.deleted.message',
                                args: [message(code: 'AskingVisit.label',
                                        default: 'AskingVisit'),
                                       askingVisitInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(
                        code: 'default.not.found.message',
                        args: [message(code: 'askingVisit.label',
                                default: 'AskingVisit'),
                               params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
