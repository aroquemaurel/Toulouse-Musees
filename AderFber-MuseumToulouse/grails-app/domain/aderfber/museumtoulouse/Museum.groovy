package aderfber.museumtoulouse

class Museum {
    String name
    String openingHours
    String phone
    String subwayAccess
    String busAccess
    Address address

    static constraints = {
        name blank : false
        openingHours blank: false
        phone blank: true, nullable: true
        subwayAccess blank : true, nullable: true
        busAccess blank : true, nullable: true
        address nullable: false
    }
}
