package aderfber.museumtoulouse



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AskingMuseumVisitController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AskingMuseumVisit.list(params), model:[askingMuseumVisitInstanceCount: AskingMuseumVisit.count()]
    }

    def show(AskingMuseumVisit askingMuseumVisitInstance) {
        respond askingMuseumVisitInstance
    }

    def create() {
        respond new AskingMuseumVisit(params)
    }

    @Transactional
    def save(AskingMuseumVisit askingMuseumVisitInstance) {
        if (askingMuseumVisitInstance == null) {
            notFound()
            return
        }

        if (askingMuseumVisitInstance.hasErrors()) {
            respond askingMuseumVisitInstance.errors, view:'create'
            return
        }

        askingMuseumVisitInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'askingMuseumVisit.label', default: 'AskingMuseumVisit'), askingMuseumVisitInstance.id])
                redirect askingMuseumVisitInstance
            }
            '*' { respond askingMuseumVisitInstance, [status: CREATED] }
        }
    }

    def edit(AskingMuseumVisit askingMuseumVisitInstance) {
        respond askingMuseumVisitInstance
    }

    @Transactional
    def update(AskingMuseumVisit askingMuseumVisitInstance) {
        if (askingMuseumVisitInstance == null) {
            notFound()
            return
        }

        if (askingMuseumVisitInstance.hasErrors()) {
            respond askingMuseumVisitInstance.errors, view:'edit'
            return
        }

        askingMuseumVisitInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AskingMuseumVisit.label', default: 'AskingMuseumVisit'), askingMuseumVisitInstance.id])
                redirect askingMuseumVisitInstance
            }
            '*'{ respond askingMuseumVisitInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AskingMuseumVisit askingMuseumVisitInstance) {

        if (askingMuseumVisitInstance == null) {
            notFound()
            return
        }

        askingMuseumVisitInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AskingMuseumVisit.label', default: 'AskingMuseumVisit'), askingMuseumVisitInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'askingMuseumVisit.label', default: 'AskingMuseumVisit'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
