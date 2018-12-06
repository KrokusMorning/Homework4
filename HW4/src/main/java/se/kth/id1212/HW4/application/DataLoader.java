package se.kth.id1212.HW4.application;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.kth.id1212.HW4.domain.Currency;
import se.kth.id1212.HW4.repository.CurrencyRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Component
public class DataLoader implements ApplicationRunner {

    private CurrencyRepository currencyRepository;

    @Autowired
    public DataLoader(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void run(ApplicationArguments args) {

        currencyRepository.save(currencyFactory("EUR"));
        currencyRepository.save(currencyFactory("USD"));
        currencyRepository.save(currencyFactory("GBP"));
        currencyRepository.save(currencyFactory("SEK"));
        currencyRepository.save(currencyFactory("CAD"));
        currencyRepository.save(currencyFactory("BTC"));

    }

    private Currency currencyFactory(String currency){
        Double value = null;
        try {
            URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q=EUR_" + currency + "&compact=yjava%20get%20json");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String jsonString = br.readLine();
            JSONObject jsonObject = new JSONObject(jsonString);
            value = Double.valueOf(jsonObject.getJSONObject("results").getJSONObject("EUR_" + currency).getString("val"));
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return new Currency(currency, value);
    }
}
