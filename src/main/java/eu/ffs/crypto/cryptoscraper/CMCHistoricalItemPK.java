package eu.ffs.crypto.cryptoscraper;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class CMCHistoricalItemPK implements Serializable {

    private String id;
    private LocalDate date;

    public CMCHistoricalItemPK(String id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public CMCHistoricalItemPK() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
