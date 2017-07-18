package com.ttn.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ttn.enums.Visibility;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name" , "createdBy"}))
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name ;
    @OneToOne
    @JoinColumn(name = "createdBy")
    private User createdBy;
    private Date dateCreated;
    private Date lastUpdated ;
    
    
    public List<Resource> getR() {
		return r;
	}

	public void setR(List<Resource> r) {
		this.r = r;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE , mappedBy = "topic")
	List<Subscription> s = new ArrayList<Subscription>() ;
	
	@JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE , mappedBy = "topic")
	List<Resource> r = new ArrayList<Resource>() ;
	
    public List<Subscription> getS() {
		return s;
	}

	public void setS(List<Subscription> s) {
		this.s = s;
	}

	@Enumerated(EnumType.STRING)
    private Visibility visibility;
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
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

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
}
