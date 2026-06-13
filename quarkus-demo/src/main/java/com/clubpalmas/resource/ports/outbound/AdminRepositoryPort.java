package com.clubpalmas.resource.ports.outbound;

import java.util.Optional;

import com.clubpalmas.resource.domain.model.Admin;

public interface AdminRepositoryPort {
    Optional<Admin> buscarPorEmail(String email);
}
