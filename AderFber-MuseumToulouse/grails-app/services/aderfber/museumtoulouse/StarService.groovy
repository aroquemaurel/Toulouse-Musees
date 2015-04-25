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
    def addStar(Museum m) {
        if (!stars*.id.contains(m.id)) {
            stars.add(m)
        }
    }

    /**
     * Remove a new museum <i>m</i> to list of stars.
     * @param m Museum to remove
     * @return List of museums
     */
    def removeStar(Museum toRemove) {
        for(Museum m : stars ) {
            if(m.id == toRemove.id) {
                stars.remove(m)
                break
            }
        }
    }

    /**
     * Return TRUE if the museum <i>m</i> has already added to list of stars,
     * else return FALSE
     * @param m Museum
     * @return boolean
     */
    boolean isStar(Museum m) {
        return stars*.id.contains(m.id)
    }
}
