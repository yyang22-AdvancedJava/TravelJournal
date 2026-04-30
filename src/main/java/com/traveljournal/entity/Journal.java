package com.traveljournal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;

/**
 * Represents a travel journal entry in the system.
 *
 * @author yyang22
 */
@Entity
@Table(name = "journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    /**
     * The user who authored this journal entry.
     * Established as a Many-to-One relationship.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    /**
     * The geographic location associated with this journal entry.
     * Established as a Many-to-One relationship.
     */
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @JsonBackReference
    private Location location;

    private String title;
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "weather")
    private String weather;

    /**
     * Default no-argument constructor required by JPA/Hibernate.
     */
    public Journal() {}

    // Getter & Setter
    /**
     * Gets the unique identifier for the journal.
     * @return the journal id
     */
    public int getId() { return id; }
    /**
     * Sets the unique identifier for the journal.
     * @param id the journal id
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gets the user who authored this journal entry.
     * @return the author user
     */
    public User getUser() { return user; }
    /**
     * Sets the user who authored this journal entry.
     * @param user the author user
     */
    public void setUser(User user) { this.user = user; }

    /**
     * Gets the geographic location associated with this journal.
     * @return the associated location
     */
    public Location getLocation() { return location; }
    /**
     * Sets the geographic location associated with this journal.
     * @param location the associated location
     */
    public void setLocation(Location location) { this.location = location; }

    /**
     * Gets the title of the journal entry.
     * @return the journal title
     */
    public String getTitle() { return title; }
    /**
     * Sets the title of the journal entry.
     * @param title the journal title
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Gets the main content of the journal entry.
     * @return the journal content
     */
    public String getContent() { return content; }
    /**
     * Sets the main content of the journal entry.
     * @param content the journal content
     */
    public void setContent(String content) { this.content = content; }

    /**
     * Gets the timestamp when the journal was created.
     * @return the creation date and time
     */
    public LocalDateTime getCreatedAt() { return createdAt; }
    /**
     * Sets the timestamp when the journal was created.
     * @param createdAt the creation date and time
     */
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    /**
     * Gets the timestamp when the journal was last updated.
     * @return the last update date and time
     */
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    /**
     * Sets the timestamp when the journal was last updated.
     * @param updatedAt the last update date and time
     */
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    /**
     * Gets the recorded weather for the travel date.
     * @return the weather description
     */
    public String getWeather() { return weather; }
    /**
     * Sets the recorded weather for the travel date.
     * @param weather the weather description
     */
    public void setWeather(String weather) { this.weather = weather; }

    /**
     * Returns a string representation of the journal
     * @return a string describing the journal
     */
    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", weather='" + weather + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Removes relationships with other entities by nullifying user and location references.
     * This utility method is used to clean up foreign key associations, typically
     * before the journal entry is deleted from the system.
     */
    // Journal 엔티티에 관계 해제 메서드 추가
    //Journal 클래스에 Location과의 관계를 정리하는 메서드를 만드세요
    public void removeDependencies() {
        this.user = null;
        this.location = null; // Location과의 연결도 명시적으로 끊어줍니다.
    }
}