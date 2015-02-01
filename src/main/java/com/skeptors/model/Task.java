package com.skeptors.model;

import org.datanucleus.api.jpa.annotations.Extensions;

import javax.jdo.annotations.PrimaryKey;
import javax.persistence.*;
import java.util.Collection;

/**
 * Created by vamshi on 9/1/15.
 */
@Entity
public class Task implements Comparable<Task>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Extensions(venderNa)
    private Long id;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    private String encodedKey;

    private String description;
    private int index;

    @ManyToOne
    private Task parent;

    @OneToMany(mappedBy = "parent")
    private Collection<Task> children;

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }

    public Collection<Task> getChildren() {
        return children;
    }

    public void setChildren(Collection<Task> children) {
        this.children = children;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Task otherTask) {
        return this.getIndex()-otherTask.getIndex();
    }
}
