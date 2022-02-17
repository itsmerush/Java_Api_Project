package com.practice.second;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.*;
import java.util.Date;

@Entity
@Table(name = "details")
public class UserModel {


    @Column(name = "BPN")
    String BPN;

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "Serial_No")
    Long Serial_No;

    @Column(name = "PCN")
    String PCN;

    @Column(name = "Spend")
    Double Spend;

    @Column(name = "Transaction_Date")
    String Transaction_Date;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Insert_TIme")
    public Date Insert_TIme = new java.sql.Date(new java.util.Date().getTime());

    @Column(name = "Country_Code")
    String Country_Code;


    @Column(name = "Batch_Id")
    int Batch_Id;

    public int getBatch_Id() {
        return Batch_Id;
    }

    public void setBatch_Id(int batch_Id) {
        Batch_Id = batch_Id;
    }

    public String getBPN() {
        return BPN;
    }

    public void setBPN(String BPN) {
        this.BPN = BPN;
    }

    public Long getSerial_No() {
        return Serial_No;
    }

    public void setSerial_No(Long serial_No) {
        Serial_No = serial_No;
    }

    public String getPCN() {
        return PCN;
    }

    public void setPCN(String PCN) {
        this.PCN = PCN;
    }

    public Double getSpend() {
        return Spend;
    }

    public void setSpend(Double spend) {
        Spend = spend;
    }

    public String getTransaction_Date() {
        return Transaction_Date;
    }

    public void setTransaction_Date(String transaction_Date) {
        Transaction_Date = transaction_Date;
    }

    public Date getInsert_TIme() {
        return Insert_TIme;
    }

    public void setInsert_TIme(Date insert_TIme) {
        Insert_TIme = insert_TIme;
    }

    public String getCountry_Code() {
        return Country_Code;
    }

    public void setCountry_Code(String country_Code) {
        Country_Code = country_Code;
    }





}
