package aderfber.museumtoulouse

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(StarController)
@Mock([Museum, Address, Manager])
class StarControllerSpec extends Specification {
    Address address
    Manager manager

    def setup() {
        controller.starService = new StarService()
        address = new Address(
                number: "2",
                street: "RUE VIGUERIE",
                postalCode: "31300",
                city: "TOULOUSE").save(failOnError: true)

        manager = new Manager(name: "Association").save(failOnError: true)

    }

    def populateValidParams(params) {
        assert params != null
        params["museumId"] = '1'
        params["name"] = 'MUSEE DE L\'HISTOIRE DE LA MEDECINE DE TOULOUSE'
        params["openingHours"] = 'Ouvert tous les jeudi et vendredi de 11h à ' +
                '17h, et le dimande de 10h à 18h.Visites guidées sur demande.'
        params["phone"] = '05 61 77 84 25'
        params["subwayAccess"] = 'Saint-Cyprien-République, Esquirol (A)'
        params["busAccess"] = '2, 10, 12, 14, 78, 80'
        params["address"] = address
        params["manager"] = manager

    }

    def cleanup() {
    }

    def "Test if a museum is added to the list of stars"() {
        when: "add to stars of a museum"
        if (controller?.session?.id) {
            controller.addToStars()
        }


        then: "A museum is add to the list of stars"
        controller.starService.stars.size() == 1
    }

    def "Test if a museum is not added to the list of stars when it already exists"() {
        when: "add to stars of a museum"
        controller.addToStars()

        then: "A museum is add to the list of stars"
        controller.starService.stars.size() == 1
    }

    def "Test if a museum is removed to the list of stars"() {
        when: "add to stars of a museum"
        controller.removeToStars()

        then: "A museum is add to the list of stars"
        controller.starService.stars.size() == 0
    }

    def "Test if a museum is not removed to the list of stars when it does not contain into" () {
        when: "add to stars of a museum"
        controller.removeToStars()

        then: "A museum is add to the list of stars"
        controller.starService.stars.size() == 0
    }

}
