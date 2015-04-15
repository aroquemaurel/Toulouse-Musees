package aderfber.museumtoulouse

import spock.lang.Specification

/**
 * Created by florent on 13/04/15.
 */
class MuseumServiceIntegrationSpec extends Specification {
    Address address
    Manager manager
    TestSetMuseumCsvService testSetMuseumCsvService

    MuseumService museumService

    def setup() {
        address =
                new Address(
                        number: "2",
                        street: "RUE VIGUERIE",
                        postalCode: "31300",
                        city: "TOULOUSE"
                )
        manager = new Manager(name: "Association");
    }

    void "test for inserting or updating a museum"() {

        given: "un museum"
        Museum museum =
                new Museum(
                        name: "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE",
                        openingHours: "Ouvert tous les jeudi et vendredi de" +
                                " 11h à 17h, et le dimande de 10h à 18h." +
                                "Visites guidées sur demande.",
                        phone: "05 61 77 84 25",
                        subwayAccess: "Saint-Cyprien-République, Esquirol (A)",
                        busAccess: "2, 10, 12, 14, 78, 80",
                        address: address,
                        manager: manager
                )

        when: "we insert or update museum"
        Museum resultMuseum =
                museumService.insertOrUpdateMuseum(
                        museum, address, manager);

        then: "museum inserted is that returned"
        resultMuseum == museum

        and:"museum has an id"
        museum.id != null

        and:"is a valid museum"
        museum.validate()

        and:"is museum stored in database"
        Museum.findById(museum.id) != null

        and :"properties of museum are also updating"
        museum.address == address
        museum.manager == manager
    }

    void "test on remove a museum"() {

        given:"a museum existing in database"
        Museum museum =
                new Museum(
                        name: "MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE",
                        openingHours: "Ouvert tous les jeudi et vendredi de" +
                                " 11h à 17h, et le dimande de 10h à 18h." +
                                "Visites guidées sur demande.",
                        phone: "05 61 77 84 25",
                        subwayAccess: "Saint-Cyprien-République, Esquirol (A)",
                        busAccess: "2, 10, 12, 14, 78, 80",
                        address: address,
                        manager: manager
                )
        Museum museumInsertOrUpdate =
                museumService.insertOrUpdateMuseum(museum,
                        address, manager);


        when:"we throw a deletion of the museum"
        museumService.deleteMuseum(museum)

        then:"Museum is removed in database"
        //Museum.findById(museum.id) == null
        //Address.findById(address.id) == null

        and:" Manager is not removed"
        //Manager.findById(manager.id) != null

    }

    /*
    void "test searchengine on museums"() {

        given:"addresses, managers and museums provide by test set"
        testSetService
        testSetService.parseCsvMuseumFile()

        when:"we search museums where mueseum name contains 'EROT'  "
        List<Museum> res = museumService.searchMuseums("EROT",null,null)

        then:"we get the only museum"
        res.size() == 1
        //res*.id.contains(testSetService.jeanneOnAct1.id)
        //res*.id.contains(testSetService.jacquesOnAct1.id)



        when:"on cherche les inscriptions dont les activités sont gérées par le responsable dont le nom ou le prenom contient 'Val'"
        res = museumService.searchMuseums(null,'Val',null)

        then:"on récupère uniquement l'inscription jacquesOnAct3"
        res.size() == 1
        res*.id.contains(testSetService.jacquesOnAct3.id)

        when:"on cherche les inscriptions sur lesquelles une personne dont le nom ou me prénom contient 'Jack' "
        res = museumService.searchMuseums(null,null,'Jacq')

        then:"on recupère les 2 inscriptions de Jacques"
        res.size() == 2
        res*.id.contains(testSetService.jacquesOnAct3.id)
        res*.id.contains(testSetService.jacquesOnAct1.id)

        and:"elle sont ordonnées suivant le titre de l'activité"
        res*.activite*.titre == [testSetService.activite1.titre, testSetService.activite3.titre]

        when:"on cherche les inscriptions sur lesquelles une personne dont le nom ou me prénom contient 'Jack'et dont les activités sont gérées par le responsable dont le nom ou le prenom contient 'Isa'  "
        res = museumService.searchMuseums(null,'Isa','Jacq')

        then:"on récupère uniquement l'inscription jacquesOnAct1"
        res.size() == 1
        res*.id.contains(testSetService.jacquesOnAct1.id)

        when:"on cherche les inscriptions dont le titre de l'activité contient 'Isa'"
        res = museumService.searchMuseums("Isa",null,null)

        then: "on ne récupère aucune inscription"
        res.size() == 0

        when:"on positionne tous les critères à null"
        res = museumService.searchMuseums(null, null, null)

        then: "on récupère toutes les inscriptions"
        res.size() == 3

        and:"elle sont ordonnées suivant le titre de l'activité"
        res*.activite*.titre == [testSetService.activite1.titre, testSetService.activite1.titre, testSetService.activite3.titre]

    }
*/
}
