package eu.ffs.crypto.cryptoscraper;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CMCGlobalItemRepository extends PagingAndSortingRepository<CMCGlobalItem, String> {

    @Query("select id from CMCGlobalItem")
    public List<String> getSymbols();
}
