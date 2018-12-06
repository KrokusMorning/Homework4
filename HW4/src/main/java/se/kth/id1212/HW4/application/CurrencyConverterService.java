package se.kth.id1212.HW4.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.id1212.HW4.domain.CurrencyDTO;
import se.kth.id1212.HW4.repository.CurrencyRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class CurrencyConverterService {

    @Autowired
    CurrencyRepository currencyRepository;

    public CurrencyDTO findCurrencyByName(String name){
        return currencyRepository.findCurrencyByName(name);
    }

    public List<? extends CurrencyDTO> getAllCurrencies(){
        return currencyRepository.findAll();
    }

    public Double calculateConvertedAmount(Double amount, Double fromValue, Double toValue){
        return round(((toValue/fromValue) * amount), 3);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
