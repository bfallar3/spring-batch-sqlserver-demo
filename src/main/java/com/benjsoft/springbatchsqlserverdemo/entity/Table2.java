package com.benjsoft.springbatchsqlserverdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table2 {
    @Id
    private Long id;
    private Long table1Id;
    private String data;
    private boolean deleted;
}