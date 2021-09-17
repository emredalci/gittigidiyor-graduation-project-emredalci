package dev.patika.creditscoreservice.util;

import dev.patika.creditscoreservice.model.CreditScore;
import dev.patika.creditscoreservice.repository.CreditScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitializerRunner implements CommandLineRunner {

    private final CreditScoreRepository creditScoreRepository;

    @Override
    public void run(String... args) throws Exception {

        CreditScore creditScore1 = CreditScore.builder().nationalId("11111111110").score(600).build();
        CreditScore creditScore2 = CreditScore.builder().nationalId("11111111112").score(600).build();
        CreditScore creditScore3 = CreditScore.builder().nationalId("11111111113").score(600).build();
        CreditScore creditScore4 = CreditScore.builder().nationalId("11111111114").score(600).build();
        CreditScore creditScore5 = CreditScore.builder().nationalId("11111111115").score(600).build();
        CreditScore creditScore6 = CreditScore.builder().nationalId("11111111116").score(600).build();
        CreditScore creditScore7 = CreditScore.builder().nationalId("11111111117").score(600).build();
        CreditScore creditScore8 = CreditScore.builder().nationalId("11111111118").score(600).build();
        CreditScore creditScore9 = CreditScore.builder().nationalId("11111111119").score(600).build();
        CreditScore creditScore10 = CreditScore.builder().nationalId("11111111100").score(600).build();
        CreditScore creditScore11 = CreditScore.builder().nationalId("11111111110").score(600).build();
        CreditScore creditScore12 = CreditScore.builder().nationalId("11111111120").score(600).build();
        CreditScore creditScore13 = CreditScore.builder().nationalId("11111111130").score(600).build();
        CreditScore creditScore14 = CreditScore.builder().nationalId("11111111140").score(600).build();
        CreditScore creditScore15 = CreditScore.builder().nationalId("11111111150").score(600).build();

        creditScoreRepository.save(creditScore1);
        creditScoreRepository.save(creditScore2);
        creditScoreRepository.save(creditScore3);
        creditScoreRepository.save(creditScore4);
        creditScoreRepository.save(creditScore5);
        creditScoreRepository.save(creditScore6);
        creditScoreRepository.save(creditScore7);
        creditScoreRepository.save(creditScore8);
        creditScoreRepository.save(creditScore9);
        creditScoreRepository.save(creditScore10);
        creditScoreRepository.save(creditScore11);
        creditScoreRepository.save(creditScore12);
        creditScoreRepository.save(creditScore13);
        creditScoreRepository.save(creditScore14);
        creditScoreRepository.save(creditScore15);


    }
}
