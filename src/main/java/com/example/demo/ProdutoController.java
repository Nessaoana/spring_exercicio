package com.example.demo;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entidades.Produto;
import com.example.demo.entidades.ProdutoRepository;
import com.example.demo.entidades.ResourceNotFoundException;
import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.UsuariosRepository;

@Controller
public class ProdutoController {
	
	private ProdutoRepository repository;

	
	public ProdutoController(ProdutoRepository repository) {
		super();
		this.repository = repository;
	}


	@GetMapping("/produtos")
	public String index(Model model) {
		model.addAttribute("produtos", repository.findAll());
		
		return "produtos/list";
	}
	
	
	@GetMapping("/produtos/create")
	public String create(Produto produto) {
		return "produtos/create";
	}
	
	
	@PostMapping("/produtos")
	public String create(@Valid Produto produto, BindingResult result , Model model ) {
		if(result.hasErrors())
				return "produtos/create";
		
		repository.save(produto);
		
		// usuario.salvar();
		
		model.addAttribute("produtos", repository.findAll());
		
		return "produtos/list";
	}

	
	@PostMapping("produtos/{id}")
	public String edit(@PathVariable(name="id") long id, @Valid Produto produto, BindingResult result , Model model ) {
		if(result.hasErrors()) {
				return "produtos/edit"; 
		}				
		repository.save(produto);
		
		model.addAttribute("produtos", repository.findAll());
		
		return "produtos/list";
	}
	
	@GetMapping("/produtos/edit/{id}")
	public String edit(@PathVariable(name="id") long id, Model model ) {
		
		
			Produto produto = repository
					.findById(id)
					.orElseThrow( () -> new ResourceNotFoundException("Produto não encontrado"));

		model.addAttribute("produto", produto);
		
		
		return "produtos/edit";
	}
	
	
	@GetMapping("/produtos/delete/{id}")
	public String delete(@PathVariable(name="id") long id, Model model ) {
		
		repository.deleteById(id);
		
		model.addAttribute("produtos", repository.findAll());
			
		return "produtos/list";

	}
	
}
