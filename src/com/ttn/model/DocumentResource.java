package com.ttn.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Document")
public class DocumentResource extends Resource {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
