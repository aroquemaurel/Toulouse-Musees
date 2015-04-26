package aderfber.museumtoulouse

import grails.transaction.Transactional

@Transactional
class StarController {

    static sessionScope = "favori"

    /**
     * Star service to manage museum stars of user.
     */
    StarService starService

    /**
     * Add the museum <i>m</i>  to the stars list.
     * @param m Museum
     * @return stars list
     */
    def addToStars() {
        starService.addStar(Museum.findById(params['museumId'] as Long))
        redirect(controller: "museum", action: "doResearch", params: [successToStars: 1])
    }

    /**
     * Remove the museum <i>m</i>  to the stars list.
     * @param m Museum
     * @return stars list
     */
    def removeToStars() {
        starService.removeStar(Museum.findById(params['museumId'] as Long))
        redirect(controller: "museum", action: "doResearch", params: [successUnstars: 1])
    }


    /**
     * Return TRUE if the museum <i>m</i> has already added to list of stars,
     * else return FALSE
     * @param m Museum
     * @return boolean
     */
    boolean isStar(Museum m) {
        return starService.isStar(m)
    }



}
