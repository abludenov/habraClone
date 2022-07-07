package com.javamentor.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", unique = true, nullable = false)
    private String companyName;

    @Column(name = "utr_number", unique = true, nullable = false)
    private Long UTRnumber;

    @Column(name = "company_site", unique = true, nullable = false)
    private String companySite;

    @Column(name = "company_scale", nullable = false)
    private Long companyScale;

    @Column(name = "company_avatar")
    private byte[] companyAvatar;

    @Column(name = "about_company", nullable = false)
    private String aboutCompany;

    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    @ElementCollection
    private List<String> companyContacts;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_rating_id")
    private Rating companyRating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "company_admins",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> companyAdmins;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "company_moderators",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> companyModerators;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "company_hubs",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "hub_id"))
    private Set<Hub> companyHubs;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "company_blog",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> companyBlog;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "company_tags",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> companyTags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "company_followers",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private Set<User> companyFollowers;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        return id.equals(company.id)
                && companyName.equals(company.companyName)
                && UTRnumber.equals(company.UTRnumber);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(id, companyName, UTRnumber);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", UTRnumber=" + UTRnumber +
                ", companySite='" + companySite + '\'' +
                ", companyScale=" + companyScale +
                ", aboutCompany='" + aboutCompany + '\'' +
                ", companyRating=" + companyRating +
                '}';
    }
}