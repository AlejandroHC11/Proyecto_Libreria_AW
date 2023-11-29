package org.cibertec.edu.pe.modelo.controller;

import java.util.List;

import org.cibertec.edu.pe.modelo.Usuarios;
import org.cibertec.edu.pe.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private IUsuariosRepository usuarioRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", ""); // Inicializar el mensaje de error como vacío al cargar el formulario de login
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("usuario") String usuario,
                        @RequestParam("clave") String clave,
                        Model model) {
        List<Usuarios> usuarios = usuarioRepository.findByUsuario(usuario);

        if (usuarios.isEmpty()) {
            model.addAttribute("error", "Usuario no Registrado");
            return "login";
        }

        boolean usuarioValido = false;

        for (Usuarios user : usuarios) {
            if (user.getClave().equals(clave)) {
                usuarioValido = true;
                break;
            }
        }

        if (usuarioValido) {
            return "redirect:/inicio";
        } else {
            model.addAttribute("error", "Contraseña incorrecta");
            return "login";
        }
    }
}