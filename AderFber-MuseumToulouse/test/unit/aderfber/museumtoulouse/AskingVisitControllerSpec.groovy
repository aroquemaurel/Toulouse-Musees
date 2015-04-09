package aderfber.museumtoulouse


import grails.test.mixin.*
import spock.lang.*

@TestFor(AskingVisitController)
@Mock(AskingVisit)
class AskingVisitControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["code"] = 42
        params["beginPeriodDate"] = new Date(2010, 5, 5)
        params["endPeriodDate"] = new Date(2015, 5, 5)
        params["nbPeople"] = 1
        params["status"] = 1
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.askingVisitInstanceList
        model.askingVisitInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.askingVisitInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def askingVisit = new AskingVisit()
        askingVisit.validate()
        controller.save(askingVisit)

        then: "The create view is rendered again with the correct model"
        model.askingVisitInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        askingVisit = new AskingVisit(params)

        controller.save(askingVisit)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/askingVisit/show/1'
        controller.flash.message != null
        AskingVisit.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def askingVisit = new AskingVisit(params)
        controller.show(askingVisit)

        then: "A model is populated containing the domain instance"
        model.askingVisitInstance == askingVisit
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def askingVisit = new AskingVisit(params)
        controller.edit(askingVisit)

        then: "A model is populated containing the domain instance"
        model.askingVisitInstance == askingVisit
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/askingVisit/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def askingVisit = new AskingVisit()
        askingVisit.validate()
        controller.update(askingVisit)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.askingVisitInstance == askingVisit

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        askingVisit = new AskingVisit(params).save(flush: true)
        controller.update(askingVisit)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/askingVisit/show/$askingVisit.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/askingVisit/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def askingVisit = new AskingVisit(params).save(flush: true)

        then: "It exists"
        AskingVisit.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(askingVisit)

        then: "The instance is deleted"
        AskingVisit.count() == 0
        response.redirectedUrl == '/askingVisit/index'
        flash.message != null
    }
}
