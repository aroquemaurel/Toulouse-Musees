package aderfber.museumtoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(AskingVisit)
class AskingVisitSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test a valid askingvisit"(pCode, pBeginDate, pEndDate, pNbPeople, pStatus) {
        given: "An asking visit is created"
        AskingVisit myAskingVisit = new AskingVisit(
                code: pCode,
                beginPeriodDate: pBeginDate,
                endPeriodDate: pEndDate,
                nbPeople: pNbPeople,
                status: pStatus)

        expect: "Asking visit is valid"
        myAskingVisit.validate()

        where:
        pCode   | pBeginDate          | pEndDate            | pNbPeople | pStatus
        42      | new Date(1989,7,18) | new Date(1989,7,19) | 101       | 0
        55      | new Date(2001,3,16) | new Date(2009,2,6)  | 12        | 1
        22      | new Date(2003,4,15) | new Date(2003,5,11) | 0         | 2
        2       | new Date(2003,4,15) | new Date(2003,4,15) | 0         | 2

    }

    void "test an unvalid askingvist"(pCode, pBeginDate, pEndDate, pNbPeople, pStatus) {
        given: "An asking visit is created"
        AskingVisit myAskingVisit = new AskingVisit(
                code: pCode,
                beginPeriodDate: pBeginDate,
                endPeriodDate: pEndDate,
                nbPeople: pNbPeople,
                status: pStatus)

        expect: "Asking visit is invalid"
        !myAskingVisit.validate()

        where:
        pCode   | pBeginDate            | pEndDate              | pNbPeople | pStatus
        null    | new Date(1989,7,18)   | new Date(1989,7,19)   | 101       | 0
        55      | null                  | new Date(2009,2,6)    | 12        | 1
        22      | new Date(2003,4,15)   | null                  | 0         | 2
        22      | new Date(1989,7,18)   | new Date(1989,7,19)   | -2        | 2
        22      | new Date(1989,7,18)   | new Date(1989,7,19)   | 12        | 3
        22      | new Date(1989,7,18)   | new Date(1989,7,19)   | 12        | -1
        22      | new Date(1989,7,20)   | new Date(1989,7,19)   | 12        | -1
    }
}
