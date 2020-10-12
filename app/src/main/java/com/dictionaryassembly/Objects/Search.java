package com.dictionaryassembly.Objects;

public class Search extends AssemblyForm {
    private String type;

    public Search() {
        super();
    }

    public Search(int ID, String title, String content, String type) {
        super(ID, title, content);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
