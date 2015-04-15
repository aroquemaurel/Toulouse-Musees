package aderfber.museumtoulouse

import org.springframework.transaction.annotation.Transactional

@Transactional
class MuseumService {

    /**
     * Insert or update a Museum <i>museum</i> with it <i>address</i> and
     * it <i>manager</i>.
     * @param museum Museum to insert or update
     * @param address Address of the museum
     * @param manager Manager which manage museum
     * @return Museum insert or update
     */
    Museum insertOrUpdateMuseum(
            Museum museum, Address address, Manager manager) {
        museum.setAddress(address)
        museum.setManager(manager)
        address.save(flush: true)
        manager.save(flush: true)
        museum.save(flush: true)

        return museum
    }

    /**
     * Remove the Museum and Museum address.
     * @param museum Museum to delete
     */
    void deleteMuseum(Museum museum) {
        museum.address.delete()
        museum.delete()
    }

    /**
     * Search museums which contain <i>name</i>, <i>street</i> or
     * <i>postalCode</i> and return a list of museums which correspond to the
     * request.
     * @param name Museum name
     * @param street Museum street address
     * @param postalCode Museum postal code address
     * @return List of museums
     */
    List<Museum> searchMuseums(String name, String street, String postalCode) {
        def criteria = Museum.createCriteria()
        List<Museum> museums = criteria.list {
            if (name) {
                like 'name', "%${name}%"
            }

            if (street) {
                address {
                    like 'street', "%${street}%"
                }
            }

            if (postalCode) {
                address {
                    like 'postalCode', "${postalCode}%"
                }
            }

            order('name')
        }

        return museums
    }

}
