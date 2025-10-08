package com.senac.ShelflyBackEnd.config;

import com.senac.ShelflyBackEnd.entity.Usuario;
import com.senac.ShelflyBackEnd.repository.UsuarioRepository;
import com.senac.ShelflyBackEnd.service.JwtTokenService;
import com.senac.ShelflyBackEnd.service.UsuarioDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// 💡 Removido o import de org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    // 💡 Campos definidos como 'final'
    private final JwtTokenService jwtTokenService;
    private final UsuarioRepository usuarioRoleRepository;

    // 💡 Construtor para Injeção de Dependência (substitui @Autowired nos campos)
    public UserAuthenticationFilter(JwtTokenService jwtTokenService, UsuarioRepository usuarioRoleRepository) {
        this.jwtTokenService = jwtTokenService;
        this.usuarioRoleRepository = usuarioRoleRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Sempre recupera o token, se existir
        String token = recoveryToken(request);

        // 2. Se o token existir, tenta autenticar
        if (token != null) {
            String subject = jwtTokenService.getSubjectFromToken(token);

            // Garante que o usuário existe antes de autenticar
            if (usuarioRoleRepository.findByEmail(subject).isPresent()) {
                Usuario usuarioRole = usuarioRoleRepository.findByEmail(subject).get();
                UsuarioDetailsImpl usuarioDetails = new UsuarioDetailsImpl(usuarioRole);

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(usuarioDetails.getUsername(), null, usuarioDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response); // Continua o processamento
    }

    // Recupera o token do cabeçalho Authorization da requisição
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    // 🛑 ATENÇÃO: Este método (checkIfEndpointIsNotPublic) NÃO É USADO na lógica do doFilterInternal acima.
    // Ele é redundante se você estiver configurando as rotas públicas no SecurityConfiguration.
    // Se você não usá-lo, pode removê-lo.
    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();

        if (requestMethod.equals(HttpMethod.POST.name()) && requestURI.endsWith("/api/usuario/login")) {
            return false;
        }

        if (requestMethod.equals(HttpMethod.OPTIONS.name())) {
            return false;
        }

        return Arrays.stream(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED)
                .noneMatch(publicEndpoint -> requestURI.contains(publicEndpoint));
    }
}