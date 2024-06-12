package com.benjsoft.springbatchsqlserverdemo;

import com.benjsoft.springbatchsqlserverdemo.entity.Table1;
import com.benjsoft.springbatchsqlserverdemo.entity.Table2;
import com.benjsoft.springbatchsqlserverdemo.repository.Table1Repository;
import com.benjsoft.springbatchsqlserverdemo.repository.Table2Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class SpringBatchSqlserverDemoApplication implements CommandLineRunner {

    @Autowired
    private Table1Repository table1Repository;

    @Autowired
    private Table2Repository table2Repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchSqlserverDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        log.info("Generating dump data for Table1 and Table2...");
//
//        for (int i = 6; i <= 1000000; i++) {
//            LocalDate startDate = LocalDate.now().minusYears(4);
//            LocalDate endDate = LocalDate.now().plusDays(30);
//            Table1 table1 = new Table1((long)i, "",
//                    Date.from(getRandomDateInBetween(startDate, endDate).atStartOfDay().toInstant(ZoneOffset.UTC)),
//                    getStatus());
//            table1Repository.save(table1);
//
//            Table2 table2 = new Table2((long) i, table1.getId(), "", false);
//            table2Repository.save(table2);
//        }
//
//        log.info("Dump data generated.");
    }

    private String getStatus() {
        String[] statuses = {"PENDING", "DELETED"};
        Random random = new Random();
        int index = random.nextInt(statuses.length);
        return statuses[index];
    }

    private LocalDate getRandomDateInBetween(LocalDate startDate, LocalDate endDate) {
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomEpochDay);
    }
}

