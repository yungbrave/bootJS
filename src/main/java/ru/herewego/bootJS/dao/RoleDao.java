package ru.herewego.bootJS.dao;


import ru.herewego.bootJS.model.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> setOfRoles();
    Role getRole(String role);
}