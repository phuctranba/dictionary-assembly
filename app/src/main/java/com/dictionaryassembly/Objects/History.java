package com.dictionaryassembly.Objects;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
import java.util.Date;

public class History extends AssemblyForm {

    private String type;
    private Date date;
    private Boolean active;

    public History(int ID, String title, String content, String type,Boolean active) {
        super(ID, title, content);
        this.type = type;
        this.date = new Date();
        this.active = active;
    }

    public History(int ID, String title, String content, String type) {
        super(ID, title, content);
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
