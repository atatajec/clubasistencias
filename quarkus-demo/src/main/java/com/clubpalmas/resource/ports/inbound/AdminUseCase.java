package com.clubpalmas.resource.ports.inbound;

import com.clubpalmas.resource.domain.model.Admin;

public interface AdminUseCase {
    Admin login(String email, String password);
    String loginYGenerarToken(String email, String password);
}
