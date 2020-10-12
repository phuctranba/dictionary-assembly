package com.dictionaryassembly.Objects;

import java.util.Comparator;
import java.util.Date;

public class AssemblyForm {
    private int ID;
    private String title;
    private String content;

    public AssemblyForm() {

    }

    public AssemblyForm(int ID, String title, String content) {
        this.ID = ID;
        this.title = title;
        this.content = content;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Comparator<AssemblyForm> sortTitleDecrease = new Comparator<AssemblyForm>() {
        public int compare(AssemblyForm history1, AssemblyForm history2) {
            return history1.title.compareTo(history2.title);
        }};

    public static Comparator<AssemblyForm> sortTitleIncrease = new Comparator<AssemblyForm>() {
        public int compare(AssemblyForm history1, AssemblyForm history2) {
            return history2.title.compareTo(history1.title);
        }};
}
