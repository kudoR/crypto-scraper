package eu.ffs.crypto.cryptoscraper;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class ImportData {

    private String GLOBAL_DATA_API_URL = "https://api.coinmarketcap.com/v1/ticker/?limit=100";
    private String HISTORICAL_DATA_URL = "https://coinmarketcap.com/currencies/%s/historical-data/?start=%s&end=%s"; // 20171031
    private List<CMCGlobalItem> globalItems;
    private List<CMCHistoricalItem> cmcHistoricalItems;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CMCGlobalItemRepository cmcGlobalItemRepository;

    @Autowired
    private CMCHistoricalItemRepository cmcHistoricalItemRepository;

    @Autowired
    private CryptoScraperRepository cryptoScraperRepository;

    @Scheduled(fixedRate = 60000L)
    public void importHistoricalItems() {
        CryptoScraperConfig config = cryptoScraperRepository.findOne(1);
        DateTimeFormatter API_PATTERN = DateTimeFormatter.ofPattern("yyyyMMdd");
        String lastUpdate = config != null ? config.getLastUpdate().format(API_PATTERN) : "20130428";
        String today = LocalDate.now().format(API_PATTERN);
        System.out.println("Last Update was: " + lastUpdate);
        if (!lastUpdate.equals(today)) {
            System.out.println("Performing Import!");

            this.importGlobalItems();

            cmcGlobalItemRepository.getSymbols().stream().forEach(id -> {
                try {
                    Document doc = Jsoup.connect(String.format(HISTORICAL_DATA_URL, id, lastUpdate, today)).get();
                    Elements tbody = doc.select("tbody");
                    Elements dailyEntries = tbody.select("tr");
                    System.out.println("Importing " + dailyEntries.size() + " entries for " + id);
                    for (Element dailyEntry : dailyEntries) {
                        Elements entryData = dailyEntry.select("td");
                        CMCHistoricalItem cmcHistoricalItem = new CMCHistoricalItem();

                        // date
                        Element dateElement = entryData.get(0);
                        String dateAsString = dateElement.text();
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

                        try {
                            Date dateAsUtilDate = sdf.parse(dateAsString);
                            cmcHistoricalItem.setPk(new CMCHistoricalItemPK(id, new java.sql.Date(dateAsUtilDate.getTime())));


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        // open
                        Element openElement = entryData.get(1);
                        String openAsString = openElement.text();
                        cmcHistoricalItem.setOpen(new BigDecimal(openAsString));

                        // high
                        Element highElement = entryData.get(2);
                        String highAsString = highElement.text();
                        cmcHistoricalItem.setHigh(new BigDecimal(highAsString));

                        // low
                        Element lowElement = entryData.get(3);
                        String lowAsString = lowElement.text();
                        cmcHistoricalItem.setLow(new BigDecimal(lowAsString));

                        // close
                        Element closeElement = entryData.get(4);
                        String closeAsString = closeElement.text();
                        cmcHistoricalItem.setClose(new BigDecimal(closeAsString));

                        // volume
                        Element volumeElement = entryData.get(5);
                        String volumeAsString = volumeElement.text().replaceAll(",", "");
                        if (!"-".equals(volumeAsString)) {
                            cmcHistoricalItem.setVolume(Long.parseLong(volumeAsString));
                        }

                        // marketcap
                        Element marketCapElement = entryData.get(6);
                        String marketCapAsString = marketCapElement.text().replaceAll(",", "");
                        if (!"-".equals(marketCapAsString)) {
                            cmcHistoricalItem.setMarketCap(Long.parseLong(marketCapAsString));
                        }

                        cmcHistoricalItemRepository.save(cmcHistoricalItem);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            cryptoScraperRepository.deleteAll();
            config = new CryptoScraperConfig(1, LocalDate.now());
            cryptoScraperRepository.save(config);
        } else {
            System.out.println("Last Import was today. Won't perform new Import.");
        }

    }

    private void importGlobalItems() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                GLOBAL_DATA_API_URL,
                String.class
        );
        Gson gson = new Gson();
        CMCGlobalItem[] cmcGlobalItems = gson.fromJson(response.getBody(), CMCGlobalItem[].class);

        this.globalItems = Arrays.asList(cmcGlobalItems);

        cmcGlobalItemRepository.save(globalItems);
    }
}
