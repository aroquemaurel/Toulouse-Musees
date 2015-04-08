package aderfber.museumtoulouse

class AskingVisit {
    int code
    Date beginPeriodDate
    Date endPeriodDate
    int nbPeople
    int status = 0

    static constraints = {
        code nullable: false
        beginPeriodDate nullable: false
        endPeriodDate nullable: false
        status inList: [0..2]
        nbPeople min: 0
    }
}
