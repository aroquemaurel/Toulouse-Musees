package aderfber.museumtoulouse

import grails.transaction.Transactional

@Transactional
class StarController {

    static sessionScope = "favori"

    /**
     * Star service to manage museum stars of user.
     */
    StarService starService

    def action = {
        def actionTaken = params.actionTaken
        Museum m = params.museum



        switch (actionTaken) {
            case "addToStars" :
                addToStars(m)
                break
            case "removeToStars" :
                removeToStars(m)
                break
        }
    }

    /**
     * Add the museum <i>m</i>  to the stars list.
     * @param m Museum
     * @return stars list
     */
    def addToStars() {
        starService.addStar(Museum.findById(params['museumId'] as Long))
        redirect(controller: "museum", action: "doResearch")
    }

    /**
     * Remove the museum <i>m</i>  to the stars list.
     * @param m Museum
     * @return stars list
     */
    def removeToStars() {
        starService.addStar(Museum.findById(params['museumId'] as Long))
        redirect(controller: "museum", action: "doResearch")
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
