package dev.patika.customerservice.util;

import dev.patika.customerservice.model.CreditScore;
import dev.patika.customerservice.repository.CreditScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitializerRunner implements CommandLineRunner {

    private final CreditScoreRepository creditScoreRepository;

    @Override
    public void run(String... args) throws Exception {

        CreditScore creditScore1 = CreditScore.builder().lastNumber('0').score(2000).build();
        CreditScore creditScore2 = CreditScore.builder().lastNumber('2').score(550).build();
        CreditScore creditScore3 = CreditScore.builder().lastNumber('4').score(1000).build();
        CreditScore creditScore4 = CreditScore.builder().lastNumber('6').score(400).build();
        CreditScore creditScore5 = CreditScore.builder().lastNumber('8').score(900).build();

        creditScoreRepository.save(creditScore1);
        creditScoreRepository.save(creditScore2);
        creditScoreRepository.save(creditScore3);
        creditScoreRepository.save(creditScore4);
        creditScoreRepository.save(creditScore5);

    }
}
