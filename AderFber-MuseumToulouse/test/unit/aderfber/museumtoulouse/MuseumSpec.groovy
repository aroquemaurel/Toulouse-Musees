package aderfber.museumtoulouse

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Museum)
class MuseumSpec extends Specification {

    void "test a valid museum"(pname, popeninghours, pphone, psubwayAccess, pbusAccess) {
        given: "an initialize museum"
        Museum museum = new Museum(name: pname, openingHours: popeninghours, phone: pphone,
                subwayAccess: psubwayAccess,
                busAccess: pbusAccess, address: Mock(Address), manager: Mock(Manager))

        expect: "Museum is valid"
        museum.validate()

        where:
    pname                                                           |       popeninghours                                                   |       pphone          | psubwayAccess     |       pbusAccess
        "MUSEE DES AUGUSTINS - MUSEE DES BEAUX ARTS DE TOULOUSE"    |   "Ouvert tous les jours de 9h à 19h."                                |   "05 61 36 81 12"    |   "Roseraie (A)"  |   "36, 38"
        "MUSEE DU VIEUX TOULOUSE"                                   |   "Tous les jours : 10h - 18h /  nocturne le mercredi jusqu'à 21h."   |   ""                  |   "Esquirol (A)"  |   "2, 10, 24"
        "MUSEE PAUL-DUPUY - ARTS GRAPHIQUES ET ARTS DECORATIFS"     |   "Ouvert tous les jours du 2 mai au 31 octobre de 14h00 à 18h00."    |   null                |   "Capitole (A)"  |   "2"
        "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE"            |   "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches"         |   "05 61 77 82 72"    |   ""              |   "42"
        "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE"            |   "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches"         |   "05 61 77 82 72"    |   null            |   "10"
        "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE"            |   "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches"         |   "05 61 77 82 72"    |   ""              |   "42"
        "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE"            |   "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches"         |   "05 61 77 82 72"    |   null            |   "10"
        "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE"            |   "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches"         |   "05 61 77 82 72"    |   ""              |   ""
        "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE"            |   "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches"         |   "05 61 77 82 72"    |   null            |   null
    }

    void "test an unvalid museum"(pname, popeninghours, pphone, psubwayAccess, pbusAccess, paddress, pManager) {
        given: "an initialize museum"
        Museum museum = new Museum(name: pname, openingHours: popeninghours, phone: pphone,
                subwayAccess: psubwayAccess,
                busAccess: pbusAccess, address: paddress, manager: pManager)

        expect: "Museum is valid"
        !museum.validate()

        where:
        pname                                                       |       popeninghours                                                   |       pphone          | psubwayAccess     |       pbusAccess  | paddress          | pManager
        ""                                                          |   "Ouvert tous les jours de 9h à 19h."                                |   "05 61 36 81 12"    |   "Roseraie (A)"  |   "36, 38"        |   Mock(Address)   |   Mock(Manager)
        //null                                                        |   "Tous les jours : 10h - 18h /  nocturne le mercredi jusqu'à 21h."   |   ""                  |   "Esquirol (A)"  |   "2, 10, 24"     |   Mock(Address)   |   Mock(Manager)
        "MUSEE PAUL-DUPUY - ARTS GRAPHIQUES ET ARTS DECORATIFS"     |   ""                                                                  |   null                |   "Capitole (A)"  |   "2"             |   Mock(Address)   |   Mock(Manager)
        "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE"            |   null                                                                |   "05 61 77 82 72"    |   ""              |   "42"            |   Mock(Address)   |   Mock(Manager)
        "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE"            |   "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches"         |   "05 61 77 82 72"    |   null            |   "10"            |   null            |   Mock(Manager)
        "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE"            |   "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches"         |   "05 61 77 82 72"    |   null            |   "10"            |   Mock(Address)   |   null
    }
}
