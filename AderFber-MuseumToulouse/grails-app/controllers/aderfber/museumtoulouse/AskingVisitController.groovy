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
    def formList() {
        render(view: 'list', model: [])
    }

    def askingList() {
            params.code = isCollectionOrArray(params.code) ? params.code.last() : params.code as Integer
            def errors = []
            AskingVisit a = AskingVisit.findByCode(params.code as Integer);
            if(a == null) {
                errors << "ASKING_CODE";
            }
            render(view: 'list', model: [errors: errors,
                                         askingVisit: AskingMuseumVisit.findById(a?.id)])
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
