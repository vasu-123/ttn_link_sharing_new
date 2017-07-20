package com.ttn.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Link")
public class LinkResource extends Resource {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
