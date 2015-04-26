package aderfber.museumtoulouse

import spock.lang.Specification

/**
 * Created by florent on 13/04/15.
 */
class MuseumServiceIntegrationSpec extends Specification {
    Address address
    Manager manager

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

    void "test searchengine on museums"() {

        given:"addresses, managers and museums provide by test set"
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

        when:"we search museums where mueseum name contains 'MEDECINE'  "
        List<Museum> res = museumService.searchMuseums("MEDECINE",null,null)

        then:"we get 3 museums"
        res.size() == 3
        res*.id.contains(museum.id);
        res.clear();

        when:"we search museums where mueseum address contains 'VIG'  "
        res = museumService.searchMuseums(null, "VIG", null)
        then:"we get the only museum"
        res.size() == 3
        res*.id.contains(museum.id);
        res.clear();

        when:"we search museums where mueseum postalcode contains '31300'  "
        res = museumService.searchMuseums(null, null, "31300")
        then:"we get the only museum"
        res.size() == 3
        res*.id.contains(museum.id);
        res.clear();

    }

}
