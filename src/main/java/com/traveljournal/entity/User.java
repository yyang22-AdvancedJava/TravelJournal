package com.traveljournal.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Journal> journals = new ArrayList<>();

    public User() {}

    public User(String firstName, String lastName, String userName, String password, String cognitoId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cognitoId = cognitoId;
    }

    /**
     * [추가된 핵심 메서드 1] 일기 추가 시 양방향 관계 설정
     */
    public void addJournal(Journal journal) {
        journals.add(journal);
        journal.setUser(this);
    }

    /**
     * [추가된 핵심 메서드 2] 일기 삭제 시 양방향 관계 해제
     * 이 메서드가 있어야 JournalDao에서 삭제할 때 하이버네이트 충돌이 나지 않습니다.
     */
    public void removeJournal(Journal journal) {
        journals.remove(journal);
        journal.setUser(null);
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCognitoId() { return cognitoId; }
    public void setCognitoId(String cognitoId) { this.cognitoId = cognitoId; }
    public List<Journal> getJournals() { return journals; }
    public void setJournals(List<Journal> journals) { this.journals = journals; }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", cognitoId='" + cognitoId + '\'' + '}';
    }
}