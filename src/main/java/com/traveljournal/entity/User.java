package com.traveljournal.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user within the travel journal system. *
 *
 * @author YourName
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "cognito_id", unique = true)
    private String cognitoId;

    /**
     * The collection of journal entries authored by this user.
     *
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Journal> journals = new ArrayList<>();

    /**
     * Default no-argument constructor required by JPA/Hibernate.
     */
    public User() {}

    /**
     * Parameterized constructor to initialize a user with core profile and auth data.
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param userName  the unique login name
     * @param password  the user's hashed password
     * @param cognitoId the unique identifier from AWS Cognito
     */
    public User(String firstName, String lastName, String userName, String password, String cognitoId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cognitoId = cognitoId;
    }

    /**
     * Adds a journal entry to the user's collection and establishes the
     * bidirectional relationship by setting the user on the journal object.
     *
     * @param journal the journal entry to add
     */
    public void addJournal(Journal journal) {
        journals.add(journal);
        journal.setUser(this);
    }

    /**
     * Removes a journal entry from the user's collection and breaks the
     * bidirectional relationship. This is essential for preventing
     * Hibernate conflicts during deletion.
     *
     * @param journal the journal entry to remove
     */
    public void removeJournal(Journal journal) {
        journals.remove(journal);
        journal.setUser(null);
    }

    // Getter & Setter
    /**
     * Gets the unique identifier for the user.
     * @return the user's primary key ID
     */
    public int getId() { return id; }

    /**
     * Sets the unique identifier for the user.
     * @param id the user's primary key ID
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gets the user's first name.
     * @return the first name
     */
    public String getFirstName() { return firstName; }

    /**
     * Sets the user's first name.
     * @param firstName the first name
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Gets the user's last name.
     * @return the last name
     */
    public String getLastName() { return lastName; }

    /**
     * Sets the user's last name.
     * @param lastName the last name
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Gets the unique username used for local authentication.
     * @return the username
     */
    public String getUserName() { return userName; }

    /**
     * Sets the unique username used for local authentication.
     * @param userName the unique username
     */
    public void setUserName(String userName) { this.userName = userName; }

    /**
     * Gets the user's encoded password.
     * @return the password
     */
    public String getPassword() { return password; }

    /**
     * Sets the user's encoded password.
     * @param password the password
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Gets the unique identifier provided by AWS Cognito.
     * @return the Cognito subject ID
     */
    public String getCognitoId() { return cognitoId; }

    /**
     * Sets the unique identifier provided by AWS Cognito.
     * @param cognitoId the Cognito subject ID
     */
    public void setCognitoId(String cognitoId) { this.cognitoId = cognitoId; }

    /**
     * Gets the collection of journal entries authored by this user.
     * @return a list of the user's journals
     */
    public List<Journal> getJournals() { return journals; }

    /**
     * Sets the collection of journal entries authored by this user.
     * @param journals a list of the user's journals
     */
    public void setJournals(List<Journal> journals) { this.journals = journals; }

    /**
     * Returns a string representation of the User, including the ID,
     * username, and Cognito ID for identification.
     * @return a formatted string containing user metadata
     */
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", cognitoId='" + cognitoId + '\'' + '}';
    }
}