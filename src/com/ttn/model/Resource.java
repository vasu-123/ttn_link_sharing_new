package com.ttn.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Link_Type",
        discriminatorType = DiscriminatorType.STRING)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"url" , "topic_id" , "user_username"}))
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


    private String description;

    @ManyToOne @JoinColumn(name="user_username")
    private User createdBy;

    public List<ResourceItem> getR() {
		return r;
	}

	public void setR(List<ResourceItem> r) {
		this.r = r;
	}

	@ManyToOne
    private Topic topic;
    
	@JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE , mappedBy = "resource")
	List<ResourceItem> r = new ArrayList<ResourceItem>() ;
    
	@JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE , mappedBy = "resource")
	List<ReadingItem> ri = new ArrayList<ReadingItem>() ;
    
    
    public List<ReadingItem> getRi() {
		return ri;
	}

	public void setRi(List<ReadingItem> ri) {
		this.ri = ri;
	}

	private Date dateCreated;
    private Date lastUpdated;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
}
