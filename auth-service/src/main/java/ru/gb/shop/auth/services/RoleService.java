package ru.gb.shop.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.shop.auth.entities.Role;
import ru.gb.shop.auth.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService  {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}

