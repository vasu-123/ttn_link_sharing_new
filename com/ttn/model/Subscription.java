package com.ttn.model;

import java.util.Date;

import javax.persistence.*;

import com.ttn.enums.Seriousness;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"topic_id", "user_username"}))
public class Subscription {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Seriousness seriousness;
    private Date dateCreated;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seriousness getSeriousness() {
        return seriousness;
    }

    public void setSeriousness(Seriousness seriousness) {
        this.seriousness = seriousness;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
