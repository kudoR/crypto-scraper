package eu.ffs.crypto.cryptoscraper;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class CryptoScraperConfig {
    @Id
    private Integer id;

    private LocalDate lastUpdate;

    CryptoScraperConfig(Integer id, LocalDate lastUpdate) {
        this.id = id;
        this.lastUpdate = lastUpdate;
    }

    public CryptoScraperConfig() {
    }

    LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
