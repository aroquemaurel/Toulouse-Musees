package aderfber.museumtoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Manager)
class ManagerSpec extends Specification {
    void "test a valid manager"(pfirstname, plastname) {
        given: "A manager is created"
        Manager myManager = new Manager(firstname: pfirstname, lastname: plastname)

        expect: "Manager is valid"
        myManager.validate()

        where:
        pfirstname  |   plastname
        "Bonneau"   |  "Jean"
    }

    void "test an unvalid manager"(pfirstname, plastname) {
        given: "A manager is created"
        Manager myManager = new Manager(firstname: pfirstname, lastname: plastname)

        expect: "Manager is invalid"
        !myManager.validate()

        where:
        pfirstname  |   plastname
        null        |  "Jean"
        "Bonneau"   | null
        null        | null
    }
}
