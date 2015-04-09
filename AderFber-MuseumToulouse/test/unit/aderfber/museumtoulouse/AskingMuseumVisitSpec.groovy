package aderfber.museumtoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(AskingMuseumVisit)
class AskingMuseumVisitSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test a valid askingmuseumvisit"() {
        given: "An asking visit is created"
        AskingMuseumVisit askingMuseumVisit = new AskingMuseumVisit(
                askingDate: new Date(2012,4,12),
                museum : Mock(Museum),
                askingVistit: Mock(AskingVisit))

        expect: "Asking museum visit is valid"
        askingMuseumVisit.validate()

    }

    void "test an unvalid askingmuseumvisit"(pAskingDate, pMuseum, pAskingVisit) {
        given: "An asking visit is created"
        AskingMuseumVisit askingMuseumVisit = new AskingMuseumVisit(
                askingDate: pAskingDate,
                museum : pMuseum,
                askingVistit: pAskingVisit)

        expect: "Asking museum visit is valid"
        !askingMuseumVisit.validate()

        where:
        pAskingDate         | pMuseum       | pAskingVisit
        null                | Mock(Museum)  | Mock(AskingVisit)
        new Date(2012,1,2)  | null          | Mock(AskingVisit)
        new Date(2012,1,2)  | Mock(Museum)  | null
        new Date(2012,1,2)  | null          | null
    }
}
