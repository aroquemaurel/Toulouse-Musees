package aderfber.museumtoulouse



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ManagerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Manager.list(params), model:[managerInstanceCount: Manager.count()]
    }

    def show(Manager managerInstance) {
        respond managerInstance
    }

    def create() {
        respond new Manager(params)
    }

    @Transactional
    def save(Manager managerInstance) {
        if (managerInstance == null) {
            notFound()
            return
        }

        if (managerInstance.hasErrors()) {
            respond managerInstance.errors, view:'create'
            return
        }

        managerInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message =
                        message(code: 'default.created.message',
                                args: [message(code: 'manager.label',
                                default: 'Manager'), managerInstance.id])

                redirect managerInstance
            }
            '*' { respond managerInstance, [status: CREATED] }
        }
    }

    def edit(Manager managerInstance) {
        respond managerInstance
    }

    @Transactional
    def update(Manager managerInstance) {
        if (managerInstance == null) {
            notFound()
            return
        }

        if (managerInstance.hasErrors()) {
            respond managerInstance.errors, view:'edit'
            return
        }

        managerInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message =
                        message(code: 'default.updated.message',
                                args: [message(code: 'Manager.label',
                                default: 'Manager'), managerInstance.id])
                redirect managerInstance
            }
            '*'{ respond managerInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Manager managerInstance) {

        if (managerInstance == null) {
            notFound()
            return
        }

        managerInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Manager.label', default: 'Manager'), managerInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'manager.label', default: 'Manager'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
