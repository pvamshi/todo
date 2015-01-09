package com.skeptors;

import javax.jdo.annotations.*;

/**
 * Created by vamshi on 9/1/15.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Task {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
