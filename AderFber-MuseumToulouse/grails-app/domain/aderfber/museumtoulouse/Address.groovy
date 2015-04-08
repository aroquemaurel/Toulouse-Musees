package aderfber.museumtoulouse

class Address {
    int number
    String street
    String postalCode
    String city

    static constraints = {
        street blank : true, nullable: true
        postalCode blank : false, nullable: false
        city blank : false, nullable: false
    }
}
