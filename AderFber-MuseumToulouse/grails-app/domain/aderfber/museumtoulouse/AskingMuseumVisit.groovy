package aderfber.museumtoulouse

class AskingMuseumVisit {
    Date askingDate
    static hasMany = [museums: Museum, askingMuseumVisits: AskingVisit]

    static constraints = {
        askingDate nullable: false
    }
}
