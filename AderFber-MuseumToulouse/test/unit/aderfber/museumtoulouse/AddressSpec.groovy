package aderfber.museumtoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Address)
class AddressSpec extends Specification {

    @Unroll
    void "test a valid address"(pnumber, pstreet, ppostalCode, pcity) {
        given: "An address is created"
        Address myAddress = new Address(number: pnumber, street: pstreet, postalCode: ppostalCode, city: pcity)

        expect: "Address is valid"
        myAddress.validate() == true

        where:
        pnumber  |   pstreet                        | ppostalCode | pcity
        26       |  "382-2216 Dictum Road"          | "3779"      | "Rostock"
        42       |  ""                              | "52097"     | "Lier"
        null     |  "262-6439 Consectetuer St."     | "21410"     | "Wandsworth"
        42       |  null                            | "3779"      | "Henis"
        null     |  null                            | "1337"      | "Trowbridge"
    }

    @Unroll
    void "test an unvalid address"(pnumber, pstreet, ppostalCode, pcity) {
        given: "An address is created"
        Address myAddress = new Address(number: pnumber, street: pstreet, postalCode: ppostalCode, city: pcity)

        expect: "Address is valid"
        myAddress.validate() == false

        where:
        pnumber  |   pstreet                        | ppostalCode | pcity
        26       |  "382-2216 Dictum Road"          | "3779"      | ""
        13       |  "P.O. Box 133, 3177 Erat. Road" | ""          | "Lier"
        4        |  "262-6439 Consectetuer St."     | ""          | ""
    }
}
