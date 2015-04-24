package aderfber.museumtoulouse

import grails.transaction.Transactional

@Transactional
class StarService {

    static sessionScope = "session"

    /**
     * List of museums stars.
     */
    List<Museum> stars = new ArrayList<Museum>();

    /**
     * Add a new museum <i>m</i> to list of stars.
     * @param m Museum to add
     * @return List of museums
     */
    List<Museum> addStar(Museum m) {
        if (!stars.contains(m)) {
            stars.add(m)
        }
        return stars
    }

    /**
     * Remove a new museum <i>m</i> to list of stars.
     * @param m Museum to remove
     * @return List of museums
     */
    List<Museum> removeStar(Museum m) {
        if (stars.contains(m)) {
            stars.remove(m)
        }
        return stars
    }
}
