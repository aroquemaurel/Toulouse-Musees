package aderfber.museumtoulouse

class AskingVisit {
    Integer code
    Date beginPeriodDate
    Date endPeriodDate
    int nbPeople
    int status = 0

    static constraints = {
        code nullable: false
        beginPeriodDate nullable: false
        endPeriodDate nullable: false, validator: { val, obj ->
            return val != null && obj != null && val > obj.beginPeriodDate
        }
        nbPeople min: 0, max: 6
        status inList: [0,1,2]
    }

    public String statusToString() {
        def list = ["En cours de traitement", "Accepté", "Refusé"];
        return list[status]
    }

}
