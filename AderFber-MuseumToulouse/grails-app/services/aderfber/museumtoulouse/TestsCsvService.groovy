package aderfber.museumtoulouse

import grails.transaction.Transactional

@Transactional
class TestsCsvService {
    MuseumService museumService

    def parseCsv() {
        File csv = new File("grails-app/conf/Museums.csv");
        Manager manager
        Address address
        Museum museum

        csv.splitEachLine(';') { row ->
            if (row[0] != "EQ_NOM_EQUIPEMENT") {
                manager = Manager.findByName(row[1]) ?: new Manager(name: row[1])

                address = new Address(number: row[7], street: row[8], postalCode: row[9], city: row[10])
                address = Address.find(address) ?: address

                museum = Museum.findByName(row[0]) ?: new Museum(
                        name: row[0],
                        openingHours: row[2],
                        phone: row[4],
                        subwayAccess: row[5],
                        busAccess: row[6])

                museumService.insertOrUpdateMuseum(museum, address, manager)
            }
        }
    }
}