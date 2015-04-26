package aderfber.museumtoulouse

import grails.transaction.Transactional

@Transactional
class VisitService {

    /**
     * Insert or update an AskingVisit <i>museum</i> with it <i>museum</i>
     * @param AskingVisit askingVisit to update/insert
     * @param Museum The museum who contains askingVisit
     * @param Date the insert Date
     * @return Museum insert or update
     */
    AskingMuseumVisit insertOrUpdateAskingMuseumVisit(
            AskingVisit askingVisit, Museum museum, Date d) {
        AskingMuseumVisit a = new AskingMuseumVisit()
        a.setAskingVisit(askingVisit)
        a.setAskingDate(d)
        a.setMuseum(museum)
        askingVisit.save(flush: true)
        a.save(flush: true)
        return a
    }
}
