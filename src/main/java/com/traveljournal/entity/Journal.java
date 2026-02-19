package com.traveljournal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

/**
 * A class to represent a Journal.
 *
 * @author yyang22
 */
@Entity
@Table(name = "journal") // case sensitive!!
public class Journal {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @ManyToOne
    private User user;

    private String title;

    @Lob // Large Object (TEXT)
    @Column(nullable = true)

    private String content;
    private LocalDate created_at;
    private LocalDate updated_at;
    private String location;
    private String weather;


    public Journal() {
    }

    /**
     * Instantiates a new Journal
     *
     * @param user
     * @param title
     * @param content
     * @param created_at
     * @param updated_at
     * @param location
     * @param weather
     */
    public Journal(User user, String title, String content, LocalDate created_at, LocalDate updated_at, String location, String weather) {

        this.user = user;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.location = location;
        this.weather = weather;
    }

    /**
     * Gets an id
     *
     * @return id a journal id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets an id
     * @param id a journal id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets an user
     * @return user a user info
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets an user
     * @param user a user info
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get a title
     * @return title a journal title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a title
     * @param title a journal tttle
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get content
     * @return content a journal Content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set content
     * @param content a journal content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets created date
     * @return created_at a created data
     */
    public LocalDate getCreated_at() {
        return created_at;
    }

    /**
     * SEt a created date
     * @param created_at a created date
     */
    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    /**
     * Gets an updated date
     * @return updated_at a updated date
     */
    public LocalDate getUpdated_at() {
        return updated_at;
    }

    /**
     * Set an updated date
     * @param updated_at a updated date
     */
    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    /**
     * Gets a location
     * @return location a travel place
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set a location
     * @param location a travel place
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get a weather
     * @return weather a travel place's weather
     */
    public String getWeather() {
        return weather;
    }

    /**
     * Set a weather
     * @param weather a travel place's weather
     */
    public void setWeather(String weather) {
        this.weather = weather;
    }

    /**
     * Print out the Journal info
     * @return the Journal info
     */
    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", location='" + location + '\'' +
                ", weather='" + weather + '\'' +
                '}';
    }
}
