package aderfber.museumtoulouse



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AskingMuseumVisitController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AskingMuseumVisit.list(params),
                model:[askingMuseumVisitInstanceCount: AskingMuseumVisit.count()]
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(
                        code: 'default.not.found.message',
                        args: [message(code: 'askingMuseumVisit.label',
                                default: 'AskingMuseumVisit'),
                               params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
