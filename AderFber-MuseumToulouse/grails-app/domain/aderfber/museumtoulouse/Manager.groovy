package aderfber.museumtoulouse

class Manager {
    String firstname
    String lastname

    private static hasMany = [museums:Museum]

    static constraints = {
        firstname blank: false
        lastname blank: false
    }
}
