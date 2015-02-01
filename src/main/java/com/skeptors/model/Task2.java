package com.skeptors.model;

import org.datanucleus.api.jpa.annotations.Extension;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamshi on 30/1/15.
 */
@Entity
public class Task2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String key;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Task2 parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<Task2> children = new ArrayList<>();


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Task2> getChildren() {
        return children;
    }

    public void setChildren(List<Task2> children) {
        this.children = children;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task2 getParent() {
        return parent;
    }

    public void setParent(Task2 parent) {
        this.parent = parent;
    }
}
