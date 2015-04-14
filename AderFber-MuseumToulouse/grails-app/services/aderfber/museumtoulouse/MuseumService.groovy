package aderfber.museumtoulouse

import org.springframework.transaction.annotation.Transactional

@Transactional
class MuseumService {

    /**
     *
     * @param museum
     * @param address
     * @param manager
     * @return
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
     *
     * @param museum
     */
    void deleteMuseum(Museum museum) {
        museum.address.delete()
        museum.delete()
    }
}
