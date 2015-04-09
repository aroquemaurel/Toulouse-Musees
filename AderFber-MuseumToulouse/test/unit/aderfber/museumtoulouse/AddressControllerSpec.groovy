package aderfber.museumtoulouse



import grails.test.mixin.*
import spock.lang.*

@TestFor(AddressController)
@Mock(Address)
class AddressControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["number"] = "42"
        params["street"] = "Street of bayonne"
        params["postalCode"] = "31500"
        params["city"] = "Toulouse"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.addressInstanceList
            model.addressInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.addressInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def address = new Address()
            address.validate()
            controller.save(address)

        then:"The create view is rendered again with the correct model"
            model.addressInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            address = new Address(params)

            controller.save(address)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/address/show/1'
            controller.flash.message != null
            Address.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def address = new Address(params)
            controller.show(address)

        then:"A model is populated containing the domain instance"
            model.addressInstance == address
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def address = new Address(params)
            controller.edit(address)

        then:"A model is populated containing the domain instance"
            model.addressInstance == address
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/address/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def address = new Address()
            address.validate()
            controller.update(address)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.addressInstance == address

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            address = new Address(params).save(flush: true)
            controller.update(address)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/address/show/$address.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/address/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def address = new Address(params).save(flush: true)

        then:"It exists"
            Address.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(address)

        then:"The instance is deleted"
            Address.count() == 0
            response.redirectedUrl == '/address/index'
            flash.message != null
    }
}
