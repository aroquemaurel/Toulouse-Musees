package aderfber.museumtoulouse

class AskingMuseumVisit {
    private Date askingDate
    private static hasMany = [museums: Museum, askingMuseumVisits: AskingVisit]

    static constraints = {
    }
}
