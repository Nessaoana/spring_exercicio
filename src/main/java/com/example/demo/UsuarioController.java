package com.example.demo;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.UsuariosRepository;

@Controller
public class UsuarioController {

	private UsuariosRepository repository;

	
	public UsuarioController(UsuariosRepository repository) {
		super();
		this.repository = repository;
	}


	@GetMapping("/usuarios")
	public String index(Model model) {
		model.addAttribute("usuarios", repository.findAll());
		
		return "usuarios/list";
	}
	
	
	@GetMapping("/usuarios/create")
	public String create(Usuario usuario) {
		return "usuarios/create";
	}
	
	
	@PostMapping("/usuarios")
	public String create(@Valid Usuario usuario, BindingResult result , Model model) {
		if(result.hasErrors())
			return "usuarios/create";
		
		repository.save(usuario);
		// usuario.salvar();
		
		model.addAttribute("usuarios", repository.findAll());
		
		return "usuarios/list";
	}
	
	
//	MerchandiseEntity pantsInDB = repo.findById(pantsId).get(); 
//	pantsInDB.setPrice(44.99); 
//	repo.save(pantsInDB);
}
