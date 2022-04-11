package ru.herewego.bootJS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.herewego.bootJS.dao.RoleDao;
import ru.herewego.bootJS.model.Role;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> setOfRoles() {
        return roleDao.setOfRoles();
    }

    @Override
    public Role getRole(String role) {
        return roleDao.getRole(role);
    }
}
