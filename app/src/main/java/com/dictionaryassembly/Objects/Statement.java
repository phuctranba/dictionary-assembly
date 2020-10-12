package com.dictionaryassembly.Objects;

import com.dictionaryassembly.R;

public class Statement {
    private int ID;
    private String statement;
    private String content;
    private String description;
    private boolean active;

    public Statement(int ID, String statement, String content, String description) {
        this.ID = ID;
        this.statement = statement;
        this.content = content;
        this.description = description;
        this.active = true;
    }

    public Statement(int ID, String statement) {
        this.ID = ID;
        this.statement = statement;
    }

    public Statement() { };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
