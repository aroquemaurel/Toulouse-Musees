package aderfber.museumtoulouse

class Address {
    String number
    String street
    String postalCode
    String city

    static constraints = {
        number nullable: true
        street blank : true, nullable: true
        postalCode blank : false, nullable: false
        city blank : false, nullable: false

    }
}
