package com.javamentor.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(name = "actual_name")
    private String actualName;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column(name = "about_user")
    private String aboutUser;

    @Column(name = "registered_date", nullable = false)
    private LocalDate registeredDate;

    @ElementCollection
    private List<String> specialization;

    @ElementCollection
    private List<String> contactInfo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "speakers_companies",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<Company> speakersCompanies;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_companies_follower",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_company_id"))
    private Set<Company> companiesFollower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address userLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_company_id")
    private Company userCompany;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profile profile = (Profile) o;
        return id.equals(profile.id)
                && gender == profile.gender
                && actualName.equals(profile.actualName)
                && birthDay.equals(profile.birthDay)
                && user.equals(profile.user)
                && registeredDate.equals(profile.registeredDate);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(id, gender, actualName, birthDay, registeredDate, user);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", gender=" + gender +
                ", actualName='" + actualName + '\'' +
                ", birthDay=" + birthDay +
                ", aboutUser='" + aboutUser + '\'' +
                ", registeredDate=" + registeredDate +
                ", userLocation=" + userLocation +
                ", userCompany=" + userCompany +
                ", user=" + user +
                '}';
    }

}