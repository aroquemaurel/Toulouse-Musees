package aderfber.museumtoulouse

class Museum {
    String name
    String openingHours
    String phone
    String subwayAccess
    String busAccess
    Address address
    Manager manager

    static constraints = {
        name blank : false
        openingHours blank: false
        phone blank: true, nullable: true
        subwayAccess blank : true, nullable: true
        busAccess blank : true, nullable: true
        address nullable: false
        manager nullable: false
    }

    public String getName() {
        if (name == null) {
            return ""
        }
        return name.toLowerCase().capitalize()

    }

}
