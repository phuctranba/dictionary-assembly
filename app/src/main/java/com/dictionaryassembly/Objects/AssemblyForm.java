package com.dictionaryassembly.Objects;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

public class AssemblyForm implements Serializable {
    private String ID;
    private String title;
    private String content;
    private String description;
    private String imageLink;
    private boolean active;
    private EnumType type;
    private String typeInterrupt;

    public AssemblyForm() {
    }

    public AssemblyForm(String ID,String title, String content, String description, EnumType type, String imageLink, boolean active) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.description = description;
        this.type = type;
        this.imageLink = imageLink;
        this.active = active;
    }

    public AssemblyForm(String ID,EnumType type) {
        this.ID = ID;
        this.type = type;
    }

    public AssemblyForm(String ID,String title, String typeInterrupt, String content, String description, EnumType type, String imageLink, boolean active) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.description = description;
        this.type = type;
        this.imageLink = imageLink;
        this.active = active;
        this.typeInterrupt = typeInterrupt;

    }

    public AssemblyForm(String ID, String title, String content, String description, EnumType type) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.description = description;
        this.type = type;
        this.active = true;
    }


    public AssemblyForm(String ID, String title, String content, String description, boolean active, EnumType type) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.description = description;
        this.active = active;
        this.type = type;
    }

    public AssemblyForm(String title, String content, String description, EnumType type) {
        this.title = title;
        this.content = content;
        this.description = description;
        this.type = type;
    }

    public AssemblyForm(String ID, String title, String content, EnumType type, String typeInterrupt) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.typeInterrupt = typeInterrupt;
        this.type = type;
    }

    public AssemblyForm(String title, String content, EnumType type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public static Comparator<AssemblyForm> sortTitleDecrease = new Comparator<AssemblyForm>() {
        public int compare(AssemblyForm assemblyForm1, AssemblyForm assemblyForm2) {
            return assemblyForm1.title.compareTo(assemblyForm2.title);
        }};

    public static Comparator<AssemblyForm> sortTitleIncrease = new Comparator<AssemblyForm>() {
        public int compare(AssemblyForm assemblyForm1, AssemblyForm assemblyForm2) {
            return assemblyForm2.title.compareTo(assemblyForm1.title);
        }};

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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

    public EnumType getType() {
        return type;
    }

    public void setType(EnumType type) {
        this.type = type;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getTypeInterrupt() {
        return typeInterrupt;
    }

    public void setTypeInterrupt(String typeInterrupt) {
        this.typeInterrupt = typeInterrupt;
    }
}
