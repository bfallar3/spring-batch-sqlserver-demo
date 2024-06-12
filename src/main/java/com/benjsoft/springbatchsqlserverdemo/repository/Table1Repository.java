package com.benjsoft.springbatchsqlserverdemo.repository;

import com.benjsoft.springbatchsqlserverdemo.entity.Table1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Table1Repository extends JpaRepository<Table1, Long> {
    @Query("SELECT e FROM Table1 e WHERE (CURRENT_DATE > FUNCTION('DATEADD', DAY, 30, e.expiryDate)) AND e.status <> 'DELETED'")
    List<Table1> findAllExpiredPlus30Days();
}
