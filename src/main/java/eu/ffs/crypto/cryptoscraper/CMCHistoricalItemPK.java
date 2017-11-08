package eu.ffs.crypto.cryptoscraper;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Embeddable
public class CMCHistoricalItemPK implements Serializable {

    private String id;
    private Date date;

    public CMCHistoricalItemPK(String id, Date date) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
