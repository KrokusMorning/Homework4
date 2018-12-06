package se.kth.id1212.HW4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.id1212.HW4.domain.Currency;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findCurrencyByName(String name);
    List<Currency> findAll();
}
