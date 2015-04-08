package aderfber.museumtoulouse

class Manager {
    private String firstname
    private String lastname

    private static hasMany = [museums:Museum]

    static constraints = {
    }
}
