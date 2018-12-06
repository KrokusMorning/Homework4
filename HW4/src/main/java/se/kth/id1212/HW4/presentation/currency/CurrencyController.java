package se.kth.id1212.HW4.presentation.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.id1212.HW4.application.CurrencyConverterService;


@Controller
public class CurrencyController {
    static final String CONVERT_CURRENCIES = "convert-currencies";
    private static final String ALL_CURRENCIES_OBJ = "allCurrencies";
    private static final String TO_CURRENCY_NAME = "toCurrencyName";
    private static final String VALUE = "value";

    @Autowired
    CurrencyConverterService currencyConverterService;

    @GetMapping("/")
    public String rootPage() {
        return "redirect:" + CONVERT_CURRENCIES;
    }

    @GetMapping("/" + CONVERT_CURRENCIES)
    public String showCurrenciesConversion(ConvertCurrencyForm convertCurrencyForm, Model model) {
        model.addAttribute(ALL_CURRENCIES_OBJ, currencyConverterService.getAllCurrencies());
        return CONVERT_CURRENCIES;
    }

    @PostMapping("/" + CONVERT_CURRENCIES)
    public String convertCurrencies(ConvertCurrencyForm convertCurrencyForm, Model model){
        Double convertedSum = currencyConverterService.calculateConvertedAmount(convertCurrencyForm.getAmount(),
                currencyConverterService.findCurrencyByName(convertCurrencyForm.getFromCurrencyName()).getConversionRate(),
                currencyConverterService.findCurrencyByName(convertCurrencyForm.getToCurrencyName()).getConversionRate());
        model.addAttribute(VALUE, convertedSum);
        model.addAttribute(TO_CURRENCY_NAME, convertCurrencyForm.getToCurrencyName());
        model.addAttribute(ALL_CURRENCIES_OBJ, currencyConverterService.getAllCurrencies());
        return CONVERT_CURRENCIES;
    }

}
