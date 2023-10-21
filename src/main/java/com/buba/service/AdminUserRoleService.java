package com.buba.service;

import com.buba.dao.AdminUserRoleDao;
import com.buba.pojo.AdminRole;
import com.buba.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserRoleService {
    @Autowired
    AdminUserRoleDao adminUserRoleDao;

    public List<AdminUserRole> listAllByUid(int uid){
        return adminUserRoleDao.findAllByUid(uid);
    }

    @Transactional
    public void saveRoleChanges(int uid, List<AdminRole> roles) {
        adminUserRoleDao.deleteAllByUid(uid);
        List<AdminUserRole> urs = new ArrayList<>();
        roles.forEach(r -> {
            AdminUserRole ur = new AdminUserRole();
            ur.setUid(uid);
            ur.setRid(r.getId());
            urs.add((ur));
        });
        adminUserRoleDao.saveAll(urs);
    }
}
