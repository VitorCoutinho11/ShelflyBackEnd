package com.senac.ShelflyBackEnd.service;

import com.senac.ShelflyBackEnd.dto.CreateUserDto;
import com.senac.ShelflyBackEnd.dto.LoginUserDto;
import com.senac.ShelflyBackEnd.dto.RecoveryJwtTokenDto;
import com.senac.ShelflyBackEnd.entity.Role;
import com.senac.ShelflyBackEnd.entity.Usuario;
import com.senac.ShelflyBackEnd.enums.Status;
import com.senac.ShelflyBackEnd.repository.UsuarioRepository;
// Removido o import de org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRoleService implements UserDetailsService {

    // 💡 Campos definidos como 'final'
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UsuarioRepository usuarioRoleRepository;
    private final PasswordEncoder passwordEncoder;

    // 💡 Construtor para Injeção de Dependência
    // O Spring irá injetar as dependências aqui, resolvendo o ciclo
    public UsuarioRoleService(
            @Lazy AuthenticationManager authenticationManager,
            JwtTokenService jwtTokenService,
            UsuarioRepository usuarioRoleRepository,
            PasswordEncoder passwordEncoder) {

        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.usuarioRoleRepository = usuarioRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // --- MÉTODOS DE LOGIN/AUTENTICAÇÃO ---

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        // O manager é chamado
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UsuarioDetailsImpl usuarioDetails = (UsuarioDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(usuarioDetails));
    }

    // --- MÉTODO DE CADASTRO ---

    public void createUser(CreateUserDto createUserDto) {
        Role role = new Role();
        role.setName(createUserDto.role());

        Usuario newUser = new Usuario();
        newUser.setNome(createUserDto.nome());
        newUser.setEmail(createUserDto.email());

        // Usa o PasswordEncoder injetado
        newUser.setSenha(passwordEncoder.encode(createUserDto.password()));

        newUser.setData(createUserDto.data());
        newUser.setStatus(Status.fromCodigo(createUserDto.status()));
        newUser.setRoles(List.of(role));

        usuarioRoleRepository.save(newUser);
    }

    // --- IMPLEMENTAÇÃO DE UserDetailsService ---

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRoleRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

        return new UsuarioDetailsImpl(usuario);
    }
}