package aderfber.museumtoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Manager)
class ManagerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test a valid manager"(pfirstname, plastname) {
        given: "A manager is created"
        Manager myManager = new Manager(firstname: pfirstname, lastname: plastname)

        expect: "Manager is valid"
        myManager.validate() == true

        where:
        pfirstname  |   plastname
        "Bonneau"   |  "Jean"
    }

    void "test an unvalid manager"(pfirstname, plastname) {
        given: "A manager is created"
        Manager myManager = new Manager(firstname: pfirstname, lastname: plastname)

        expect: "Manager is invalid"
        myManager.validate() == false

        where:
        pfirstname  |   plastname
        null        |  "Jean"
        "Bonneau"   | null
        null        | null
    }
}
