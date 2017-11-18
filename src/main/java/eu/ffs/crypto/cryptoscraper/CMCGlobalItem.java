package eu.ffs.crypto.cryptoscraper;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class CMCGlobalItem {

    @Id
    private String id; // 	"bitcoin"
    private String name;//	"Bitcoin"
    private String symbol;//	"BTC"
    @SerializedName("rank")
    private int rankByCap;//	"1"
    private BigDecimal price_usd;//	"7504.49"
    private BigDecimal price_btc;//	"1.0"
    @SerializedName("24h_volume_usd")
    private BigDecimal twenty_four_h_volume_usd;//	"2277740000.0"
    private Long market_cap_usd;//	"125063729190"
    private BigDecimal available_supply;//	"16665187.0"
    private BigDecimal total_supply;//	"16665187.0"
    private BigDecimal percent_change_1h;//	"-0.47"
    private BigDecimal percent_change_24h;//	"2.02"
    private BigDecimal percent_change_7d;//	"25.7"
    private Long last_updated;//	"1

    public CMCGlobalItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRank() {
        return rankByCap;
    }

    public void setRank(int rank) {
        this.rankByCap = rank;
    }

    public BigDecimal getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(BigDecimal price_usd) {
        this.price_usd = price_usd;
    }

    public BigDecimal getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(BigDecimal price_btc) {
        this.price_btc = price_btc;
    }

    public BigDecimal getTwenty_four_h_volume_usd() {
        return twenty_four_h_volume_usd;
    }

    public void setTwenty_four_h_volume_usd(BigDecimal twenty_four_h_volume_usd) {
        this.twenty_four_h_volume_usd = twenty_four_h_volume_usd;
    }

    public Long getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(Long market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public BigDecimal getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(BigDecimal available_supply) {
        this.available_supply = available_supply;
    }

    public BigDecimal getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(BigDecimal total_supply) {
        this.total_supply = total_supply;
    }

    public BigDecimal getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(BigDecimal percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public BigDecimal getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(BigDecimal percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public BigDecimal getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(BigDecimal percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public Long getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Long last_updated) {
        this.last_updated = last_updated;
    }
}
