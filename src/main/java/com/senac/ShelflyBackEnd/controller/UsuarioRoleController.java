package com.senac.ShelflyBackEnd.controller;

import com.senac.ShelflyBackEnd.dto.CreateUserDto;
import com.senac.ShelflyBackEnd.dto.LoginUserDto;
import com.senac.ShelflyBackEnd.dto.RecoveryJwtTokenDto;
import com.senac.ShelflyBackEnd.service.UsuarioRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioRoleController {

    // 💡 1. Injeção de dependência via construtor:
    //    Usamos 'final' e removemos o @Autowired do campo.
    private final UsuarioRoleService usuarioRoleService;

    // 💡 2. Construtor: O Spring injeta a dependência aqui.
    public UsuarioRoleController(UsuarioRoleService usuarioRoleService) {
        this.usuarioRoleService = usuarioRoleService;
    }

    // --- Endpoints de Autenticação e Cadastro ---

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = usuarioRoleService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    // 💡 POST para /users (Cadastro)
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto) {
        usuarioRoleService.createUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // --- Endpoints de Teste de Acesso (Segurança) ---

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/customer")
    public ResponseEntity<String> getCustomerAuthenticationTest() {
        return new ResponseEntity<>("Cliente autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/administrator")
    public ResponseEntity<String> getAdminAuthenticationTest() {
        return new ResponseEntity<>("Administrador autenticado com sucesso", HttpStatus.OK);
    }
}