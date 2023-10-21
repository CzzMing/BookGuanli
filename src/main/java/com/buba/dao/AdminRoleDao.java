package com.buba.dao;

import com.buba.pojo.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRoleDao extends JpaRepository<AdminRole,Integer> {
    AdminRole findById(int id);
}
