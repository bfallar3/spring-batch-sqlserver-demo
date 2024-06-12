package com.benjsoft.springbatchsqlserverdemo.config;

import com.benjsoft.springbatchsqlserverdemo.entity.Table1;
import com.benjsoft.springbatchsqlserverdemo.repository.Table1Repository;
import com.benjsoft.springbatchsqlserverdemo.repository.Table2Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
@Slf4j
public class UpdateTablesTasklet implements Tasklet {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Table1Repository table1Repository;

    @Autowired
    private Table2Repository table2Repository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        List<Table1> table1Items = table1Repository.findAllExpiredPlus30Days();

        if(table1Items.isEmpty()) {
            log.info("No records found for processing");
            return RepeatStatus.FINISHED;
        }

        for (Table1 record : table1Items) {
            log.info("Processing record: {}", record);

            // mark the Table1 record as expired
            record.setStatus("DELETED");
            table1Repository.save(record);

            // mark the Table2 record as deleted
            table2Repository.findById(record.getId())
                    .ifPresentOrElse((item) -> {
                        item.setDeleted(true);
                        table2Repository.save(item);
                    }, () -> {
                        log.warn("Table2 record not found for Table1 record: {}", record);
                    });
        }

        return RepeatStatus.FINISHED;
    }
}
