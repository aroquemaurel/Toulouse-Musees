package aderfber.museumtoulouse

class StarController {

    static sessionScope = "favoris"

    /**
     * Star service to manage museum stars of user.
     */
    StarService starService

    /**
     * Add the museum <i>m</i>  to the stars list.
     * @param m Museum
     * @return stars list
     */
    List<Museum> addToStars(Museum m) {
        return starService.addStar(m)
    }

    /**
     * Remove the museum <i>m</i>  to the stars list.
     * @param m Museum
     * @return stars list
     */
    List<Museum> removeToStars(Museum m) {
        return starService.addStar(m)
    }


}
