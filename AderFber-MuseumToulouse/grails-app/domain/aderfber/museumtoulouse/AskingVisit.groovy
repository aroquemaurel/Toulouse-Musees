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
        nbPeople min: 0
        status inList: [0,1,2]
    }

}
