package aderfber.museumtoulouse

import java.text.ParseException

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AskingVisitController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    StarService starService
    VisitService visitService
    boolean isCollectionOrArray(object) {
        [Collection, Object[]].any { it.isAssignableFrom(object.getClass()) }
    }

    def askingVisit() {
        Museum museum = Museum.findById(params.museumId as Long)

        if(params.beginDate && params.endDate && params.numberOfPeople) {
            boolean isValide = true
            Date begin = new Date()
            Date end = new Date()
            AskingVisit a
            params.beginDate = isCollectionOrArray(params.beginDate) ? params.beginDate.last() : params.beginDate as String
            params.endDate = isCollectionOrArray(params.endDate) ? params.endDate.last() : params.endDate as String
            params.numberOfPeople = isCollectionOrArray(params.numberOfPeople) ? params.numberOfPeople.last() : params.numberOfPeople as Integer
            try {
                begin = begin.parse("dd/MM/yyyy", params.beginDate as String)
                end   = end.parse("dd/MM/yyyy", params.endDate as String)
                Integer numberOfPeople = params.numberOfPeople as Integer;
                a = new AskingVisit(beginPeriodDate: begin, endPeriodDate: end, nbPeople: numberOfPeople)
                a.code = AskingVisit.findAll().size()+1
            } catch(RuntimeException e) {
                isValide = false
                a = new AskingVisit()
            }
            if(a.validate() && isValide) { // Forms is valide
                visitService.insertOrUpdateAskingMuseumVisit(a, museum, new Date())
                render(view: '/index', model: [successes: [a], stars      : starService.stars, museum: museum,
                                               postalCodes: Address.list([sort: "postalCode", order: "asc"]).postalCode.unique()])
            } else { // Unvalide forms
                render(view: 'index', model: [errors: ["UNVALID_ASKING_VISIT"], params: params,
                                              stars      : starService.stars, museum: Museum.findById(params.museumId as Long),
                                              postalCodes: Address.list([sort: "postalCode", order: "asc"]).postalCode.unique()])
            }
        } else { // Empty forms
            render(view: 'index', model: [stars      : starService.stars, museum: Museum.findById(params.museumId as Long),
                                          postalCodes: Address.list([sort: "postalCode", order: "asc"]).postalCode.unique()])
        }
    }

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
