package com.benjsoft.springbatchsqlserverdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table1 {
    @Id
    private Long id;
    private String data;
    private Date expiryDate;
    private String status;
}
