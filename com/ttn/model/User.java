package com.ttn.model;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User {
    private String email;
    @Id
    private String username;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE , mappedBy = "user")
	List<Subscription> s = new ArrayList<Subscription>() ;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE , mappedBy = "createdBy")
	List<Topic> t = new ArrayList<Topic>() ;

    @JsonIgnore
   @OneToMany(cascade = CascadeType.REMOVE , mappedBy = "createdBy")
	List<Resource> r = new ArrayList<Resource>() ;

    @JsonIgnore
   @OneToMany(cascade = CascadeType.REMOVE , mappedBy = "user")
	List<ResourceItem> ri = new ArrayList<ResourceItem>() ;
    
    
	
    
    public List<Subscription> getS() {
		return s;
	}

	public void setS(List<Subscription> s) {
		this.s = s;
	}

	public List<Resource> getR() {
		return r;
	}

	public void setR(List<Resource> r) {
		this.r = r;
	}

	public List<ResourceItem> getRi() {
		return ri;
	}

	public void setRi(List<ResourceItem> ri) {
		this.ri = ri;
	}

	@JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private byte[] photo;
    @Type(type="true_false")
    private Boolean admin;
    @Type(type="true_false")
    private Boolean active;
    private Date dateCreated;
    private Date lastUpdated;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
}
