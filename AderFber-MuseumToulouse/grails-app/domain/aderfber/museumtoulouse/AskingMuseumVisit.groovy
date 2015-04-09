package aderfber.museumtoulouse

class AskingMuseumVisit {
    Date askingDate
    Museum museum
    AskingVisit askingVisit

    static constraints = {
        askingDate nullable: false
        museum nullable: false
        askingVisit nullable: false
    }
}
