package com.reskill.account.repo;

import com.reskill.account.entity.UsersDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UsersDetailEntity, Integer> {
}
