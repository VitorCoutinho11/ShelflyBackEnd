package com.senac.ShelflyBackEnd.controller;

import com.senac.ShelflyBackEnd.dto.CreateUserDto;
import com.senac.ShelflyBackEnd.dto.LoginUserDto;
import com.senac.ShelflyBackEnd.dto.RecoveryJwtTokenDto;
import com.senac.ShelflyBackEnd.dto.request.UsuarioDTORequest;
import com.senac.ShelflyBackEnd.dto.response.UsuarioDTOResponse;
import com.senac.ShelflyBackEnd.entity.Usuario;
import com.senac.ShelflyBackEnd.service.UsuarioRoleService;
import com.senac.ShelflyBackEnd.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
@Tag(name = "Usuario", description = "API para gerenciamento de usuarios")
public class UsuarioController {

    // 💡 1. Torne todos os campos 'final' e remova os @Autowired de campo.
    private final UsuarioService usuarioService;
    private final UsuarioRoleService usuarioRoleService;
    private final ModelMapper modelMapper;

    // 💡 2. Construtor Corrigido: Todas as dependências são injetadas aqui.
    public UsuarioController(UsuarioService usuarioService, UsuarioRoleService usuarioRoleService, ModelMapper modelMapper){
        this.usuarioService = usuarioService;
        this.usuarioRoleService = usuarioRoleService; // <-- Injeção correta do serviço de segurança
        this.modelMapper = modelMapper;
    }

    // ----------------------------------------------------------------------------------------------
    // --- Endpoints de Autenticação e Teste (Login/Cadastro) ---
    // ----------------------------------------------------------------------------------------------

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = usuarioRoleService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    // Cadastro em POST api/usuario
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto) {
        usuarioRoleService.createUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

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


    // ----------------------------------------------------------------------------------------------
    // --- Endpoints de Gerenciamento de Usuários (CRUD) ---
    // ----------------------------------------------------------------------------------------------

    @GetMapping("/listar")
    @Operation(summary = "listar Usuarios", description = "endpoint para listar todos os usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios () {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo usuário", description = "Endpoint para criar um novo registro de usuário")
    public ResponseEntity<UsuarioDTOResponse> criarUsuario(@Valid @RequestBody UsuarioDTORequest usuario) {
        // Se este endpoint é usado apenas por Administradores, é bom manter separado do POST raiz.
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(usuario));
    }

    @PutMapping("/atualizar/{usuarioId}")
    public ResponseEntity<UsuarioDTOResponse> atualizarUsuario(
            @PathVariable Integer usuarioId,
            @RequestBody UsuarioDTORequest usuario) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuarioId, usuario));
    }

    @DeleteMapping("/apagar/{usuarioId}")
    public ResponseEntity<Void> apagarUsuario(@PathVariable Integer usuarioId) {
        usuarioService.apagarUsuario(usuarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ver")
    public ResponseEntity<List<Usuario>> verUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }
}