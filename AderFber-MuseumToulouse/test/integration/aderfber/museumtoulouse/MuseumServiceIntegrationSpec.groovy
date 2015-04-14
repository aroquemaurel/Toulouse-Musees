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

    void "test la création ou la mise à jour d'un museum"() {

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

        when: "on insert ou met à jour le museum"
        Museum resultMuseum =
                museumService.insertOrUpdateMuseum(
                        museum, address, manager);

        then: "le museum inséré est bien celui retourné"
        resultMuseum == museum

        and:"l'inscription a bien un id"
        museum.id != null

        and:"elle est valide"
        museum.validate()

        and:"elle est bien stockée en base"
        Museum.findById(museum.id) != null

        and :"les propriétes sont mises à jours comme attendues"
        museum.address == address
        museum.manager == manager
    }

    void "test de la suppression d'une inscription"() {

        given:"une inscription existante en base"
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


        when:"on déclenche la removation du museum"
        museumService.deleteMuseum(museum)

        then:"Museum is supprimé in database"
//        Museum.findById(museum.id) == null
      //  Address.findById(address.id) == null

        and:" Manager is not removed"

//        Manager.findById(manager.id) != null

    }
}
