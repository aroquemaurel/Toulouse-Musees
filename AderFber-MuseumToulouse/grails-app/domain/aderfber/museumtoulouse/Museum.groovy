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
        phone blank: true
        subwayAccess blank : true
        busAccess blank : true
        address nullable: false
    }
}
