package com.kishlaly.interviews.leverton.urlshortener.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Vladimir Kishlaly
 * @since 20.06.2017
 */
@Entity
public class Link {

    @Id
    private String id; // shortened URL is unique and can be as ID
    private String url;

    public Link() {

    }

    public Link(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }
}
