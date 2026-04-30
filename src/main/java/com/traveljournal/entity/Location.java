package com.traveljournal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a geographic destination or site associated with travel records.
 *
 * @author yyang22
 */
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "name")
    private String name;

    /**
     * The collection of journal entries associated with this location.
     */
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Journal> journals = new ArrayList<>();

    /**
     * Default no-argument constructor required by JPA/Hibernate.
     */
    public Location() {}

    /**
     * Constructs a new Location with a specific name.
     * @param name the name of the destination (e.g., "Paris", "Madison")
     */
    public Location(String name) {
        this.name = name;
    }

    // Getter & Setter
    /**
     * Gets the unique identifier for the location.
     * @return the location id
     */
    public int getId() { return id; }
    /**
     * Sets the unique identifier for the location.
     * @param id the location id
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gets the name of the location (e.g., city, country, or specific site).
     * @return the location name
     */
    public String getName() { return name; }
    /**
     * Sets the name of the location.
     * @param name the location name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets the list of journal entries associated with this specific location.
     * @return a list of journals linked to this location
     */
    public List<Journal> getJournals() { return journals; }
    /**
     * Sets the list of journal entries for this location.
     * @param journals a list of journal entries
     */
    public void setJournals(List<Journal> journals) { this.journals = journals; }
}