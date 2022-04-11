package ru.herewego.bootJS.service;

import ru.herewego.bootJS.model.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> setOfRoles();
    Role getRole(String role);
}
