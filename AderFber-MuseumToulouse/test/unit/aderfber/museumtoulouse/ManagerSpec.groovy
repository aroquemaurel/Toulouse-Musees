package aderfber.museumtoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Manager)
class ManagerSpec extends Specification {
    void "test a valid manager"() {
        given: "A manager is created"
        Manager myManager = new Manager(name: "Mairie de Toulouse - DGA Culture")

        expect: "Manager is valid"
        myManager.validate()
    }

    void "test an unvalid manager"() {
        given: "A manager is created"
        Manager myManager = new Manager(name: null)

        expect: "Manager is invalid"
        !myManager.validate()
    }
}
