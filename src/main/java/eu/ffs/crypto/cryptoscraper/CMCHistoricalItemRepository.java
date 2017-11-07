package eu.ffs.crypto.cryptoscraper;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CMCHistoricalItemRepository extends PagingAndSortingRepository<CMCHistoricalItem, CMCHistoricalItemPK> {

    @Query(name = "findById", value = "select c from CMCHistoricalItem c where c.pk.id=:id order by c.pk.date desc")
    List<CMCHistoricalItem> findByIdOrderByDateDesc(@Param("id") String id);

    //    @Query(name = "findCandidates", value = "select c1 from CMCHistoricalItem c1, CMCHistoricalItem c2 where c1.pk.id=c2.pk.id and c1.pk.date=:date1 and c2.pk.date=:date2 and c2.close<c1.close")
//    List<CMCHistoricalItem> findCandidates(@Param("date1") LocalDate date1, @Param("date1") LocalDate date2);
    @Query(name = "findCandidates", value = "select c1 from CMCHistoricalItem c1, CMCHistoricalItem c2 where c1.pk.id=c2.pk.id and c2.close<c1.close")
    List<CMCHistoricalItem> findCandidates();
}
