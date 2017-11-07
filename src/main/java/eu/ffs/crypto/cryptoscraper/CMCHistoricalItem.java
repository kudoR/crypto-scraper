package eu.ffs.crypto.cryptoscraper;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Entity
public class CMCHistoricalItem {
    @EmbeddedId
    private CMCHistoricalItemPK pk;

    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private Long volume;
    private Long marketCap;

    public String getId() {
        return pk.getId();
    }

    public String getDate() {
        return pk.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public CMCHistoricalItemPK getPk() {
        return pk;
    }

    public void setPk(CMCHistoricalItemPK pk) {
        this.pk = pk;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }
}
