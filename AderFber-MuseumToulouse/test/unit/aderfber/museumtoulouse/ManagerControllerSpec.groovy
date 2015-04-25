package aderfber.museumtoulouse



import grails.test.mixin.*
import spock.lang.*

@TestFor(ManagerController)
@Mock(Manager)
class ManagerControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.managerInstanceList
            model.managerInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.managerInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def manager = new Manager()
            manager.validate()
            controller.save(manager)

        then:"The create view is rendered again with the correct model"
            model.managerInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            manager = new Manager(params)

            controller.save(manager)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/manager/show/1'
            controller.flash.message != null
            Manager.count() == 1
    }

    void "Test the save if Manager is null"() {
        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        Manager manager
        controller.save(manager)

        then:"The create view is rendered again with the correct model"
        model.managerInstance == null
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def manager = new Manager(params)
            controller.show(manager)

        then:"A model is populated containing the domain instance"
            model.managerInstance == manager
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def manager = new Manager(params)
            controller.edit(manager)

        then:"A model is populated containing the domain instance"
            model.managerInstance == manager
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/manager/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def manager = new Manager()
            manager.validate()
            controller.update(manager)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.managerInstance == manager

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            manager = new Manager(params).save(flush: true)
            controller.update(manager)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/manager/show/$manager.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/manager/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def manager = new Manager(params).save(flush: true)

        then:"It exists"
            Manager.count() == 0

        when:"The domain instance is passed to the delete action"
            controller.delete(manager)

        then:"The instance is deleted"
            Manager.count() == 0
            response.redirectedUrl == '/manager/index'
            flash.message != null
    }
}
