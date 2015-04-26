package aderfber.museumtoulouse
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(AskingVisit)
class AskingVisitSpec extends Specification {

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
        42      | new Date(1989,7,18) | new Date(1989,7,19) | 5         | 0
        55      | new Date(2001,3,16) | new Date(2009,2,6)  | 4         | 1
        22      | new Date(2003,4,15) | new Date(2003,5,11) | 0         | 2
        2       | new Date(2003,4,15) | new Date(2003,5,15) | 0         | 2

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
        null    | new Date(1989,7,18)   | new Date(1989,7,19)   | 1         | 0
        55      | null                  | new Date(2009,2,6)    | 5         | 1
        22      | new Date(2003,4,15)   | null                  | 0         | 2
        22      | new Date(1989,7,18)   | new Date(1989,7,19)   | -2        | 2
        22      | new Date(1989,7,18)   | new Date(1989,7,19)   | 7         | 2
        22      | new Date(1989,7,18)   | new Date(1989,7,19)   | 5         | 3
        22      | new Date(1989,7,18)   | new Date(1989,7,19)   | 5         | -1
        22      | new Date(1989,7,20)   | new Date(1989,7,19)   | 5         | -1
    }


    void "test status of the asking visit"() {
        given: "An asking visit is created"
        def list = ["En cours de traitement", "Accepté", "Refusé"];
        AskingVisit myAskingVisit = new AskingVisit(
                code: 1,
                beginPeriodDate: new Date(1989,7,18),
                endPeriodDate: new Date(1989,7,19),
                nbPeople: 2,
                status: 0)

        when: "Asking visit status is 'En cours de traitement' (0)"
        myAskingVisit.status = 0

        then: "status is 'En cours de traitement'"
        myAskingVisit.statusToString() == list[0]

        when: "Asking visit status is 'Accepté' (1)"
        myAskingVisit.status = 1

        then: "Status is 'Accepté'"
        myAskingVisit.statusToString() == list[1]

        when: "Asking visit status is 'Refusé' (2)"
        myAskingVisit.status = 2

        then: "Status us 'Refusé'"
        myAskingVisit.statusToString() == list[2]

    }
}
