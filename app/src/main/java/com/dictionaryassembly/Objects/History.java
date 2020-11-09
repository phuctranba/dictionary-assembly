package com.dictionaryassembly.Objects;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

public class History extends AssemblyForm {

    private Date date;
    private String HistoryID;

    public History(String ID, String title, String content, EnumType type, Boolean active) {
        super(ID, title, content, type);
        this.date = new Date();
    }

    public History(AssemblyForm assemblyForm, String HistoryID) {
        super(assemblyForm.getID(), assemblyForm.getTitle(), assemblyForm.getContent(), assemblyForm.getType(), assemblyForm.getTypeInterrupt());
        this.date = new Date();
        this.HistoryID = HistoryID;
    }

    public History(String ID, String title, String content, EnumType type) {
        super(ID, title, content, type);
        this.date = new Date();
    }

    public History(String title, String content, EnumType type) {
        super(title, content, type);
        this.date = new Date();
    }

    public History() {
        super();
    }

    public static Comparator<History> sortDateDecrease = new Comparator<History>() {
        public int compare(History history1, History history2) {
            return history1.date.compareTo(history2.date);
        }};

    public static Comparator<History> sortDateIncrease = new Comparator<History>() {
        public int compare(History history1, History history2) {
            return history2.date.compareTo(history1.date);
        }};


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHistoryID() {
        return HistoryID;
    }

    public void setHistoryID(String historyID) {
        HistoryID = historyID;
    }
}
