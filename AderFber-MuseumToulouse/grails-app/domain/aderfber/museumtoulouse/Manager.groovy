package aderfber.museumtoulouse

class Manager {
    String firstname
    String lastname

    static constraints = {
        firstname blank: false
        lastname blank: false
    }
}
