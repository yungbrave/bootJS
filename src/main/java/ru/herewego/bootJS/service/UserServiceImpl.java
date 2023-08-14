package ru.herewego.bootJS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.herewego.bootJS.dao.UserDao;
import ru.herewego.bootJS.model.Role;
import ru.herewego.bootJS.model.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public void update(int id, User user) {
        userDao.update(id, user);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Transactional
    @Override
    public void setInitData() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        userDao.add(new User("user",
                "user@gmail.com",
                "$2a$12$vSOELe0vyyH.tbMAtlcza.snVT24qZfM1oZaCdrp5bheIqqNR/nwy",
                new HashSet<>() {{
                    add(userRole);
                }}));
        userDao.add(new User("admin",
                "admin@gmail.com",
                "$2a$12$vSOELe0vyyH.tbMAtlcza.snVT24qZfM1oZaCdrp5bheIqqNR/nwy",
                new HashSet<>() {{
                    add(userRole);
                    add(adminRole);
                }}));
    }
}
