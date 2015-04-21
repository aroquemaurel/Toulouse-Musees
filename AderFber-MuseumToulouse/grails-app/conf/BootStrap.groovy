import aderfber.museumtoulouse.TestsCsvService

class BootStrap {
    TestsCsvService testsCsvService

    def init = { servletContext ->
        testsCsvService.parseCsv()
    }
    def destroy = {
    }
}
