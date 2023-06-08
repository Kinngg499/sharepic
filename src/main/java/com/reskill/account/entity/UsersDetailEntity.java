package com.reskill.account.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users_details")
public class UsersDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "users_detail_id")
    private Integer usersDetailId;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @Column(name = "followers_count")
    private Integer followersCount;
    @Column(name = "following_count")
    private Integer followingCount;
}
