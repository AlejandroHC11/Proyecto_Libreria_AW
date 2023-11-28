package org.cibertec.edu.pe.modelo.controller;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.modelo.Categorias;
import org.cibertec.edu.pe.modelo.Productos;
import org.cibertec.edu.pe.repository.ICategoriasRepository;
import org.cibertec.edu.pe.repository.IProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductoController {
	
	@Autowired
	private IProductosRepository productoRepository;
	
	@Autowired
	private ICategoriasRepository categoriaRepository;

	//Index redireccion
	@GetMapping("index")
	public String index() {
		return "index"; 
	}
	@GetMapping({"/listar/","/listar"})
	public String listarRed() {
		return "redirect:/index";
	}
	@GetMapping({"/buscar/","/buscar"})
	public String buscarRed() {
		return "redirect:/index";
	}
	@GetMapping({"/nuevo/","/nuevo"})
	public String nuevoRed() {
		return "redirect:/index";
	}
	@GetMapping({"/modificar/","/modificar"})
	public String modificarRed() {
		return "redirect:/index";
	}
	@GetMapping({"/eliminar/","/eliminar"})
	public String eliminarRed() {
		return "redirect:/index";
	}
	//Listar
	@GetMapping("/listar/{clase}/{pagina}")
	public String listar(@PathVariable String clase, Model model,@PathVariable int pagina) {
		int pageSize = 5;
		if (clase.equals("Productos")) {			
			Page<Productos> modelPage = productoRepository.findAll(PageRequest.of(pagina-1, pageSize));
	        List<Productos> modelList = modelPage.getContent();
	        model.addAttribute("productos", modelList);
	        return "listarProducto";
	    }else if (clase.equals("Categorias")) {
	    	Page<Categorias> modelPage = categoriaRepository.findAll(PageRequest.of(pagina-1, pageSize));
	        List<Categorias> modelList = modelPage.getContent();
		    model.addAttribute("categorias", modelList);
		    return "listarCategoria";
	    }else {
	        return "redirect:/index";
	    }
	}
	//Buscar
	@GetMapping("/buscar/{clase}")
	public String buscar(@PathVariable String clase, @RequestParam(name = "descripcion", required = false) String descripcion, Model model) {
		if (clase.equals("Productos")) {
			List<Productos> lista;
		    if (descripcion != null && descripcion.trim().length() > 0) {
		        lista = productoRepository.findByDescripcion(descripcion);
		    } else {
		        lista = productoRepository.findAll();
		    }
		    model.addAttribute("productos", lista);
		    return "listarProducto";
		}else {
	        return "redirect:/index";
	    }		
	}
	//NuevoProducto
	@GetMapping("/nuevo/{clase}")
    public String nuevo(@PathVariable String clase, Model model) {
		if (clase.equals("Productos")) {
			List<Categorias> categoriasList = categoriaRepository.findAll();
	        model.addAttribute("categorias", categoriasList);
	        return "nuevoProducto";
		}else {
	        return "redirect:/index";
	    }   
    }
	//Modificar
	@GetMapping("/modificar/{clase}/{id}")
	public String modificar(@PathVariable String clase, @PathVariable int id, Model model) {
		if (clase.equals("Productos")) {
			Optional<Productos> optionalProducto = productoRepository.findById(id);
		    
		    if (optionalProducto.isPresent()) {
		        Productos producto = optionalProducto.get();
		        model.addAttribute("producto", producto);
		        
		        List<Categorias> categoriasList = categoriaRepository.findAll();
		        model.addAttribute("categorias", categoriasList);
		        
		        return "modificarProducto";
		    } else {
		        return "redirect:/listar/Productos/1";
		    }
		}else {
	        return "redirect:/index";
	    } 		
	}
	//GuardarProducto
	@PostMapping("/guardar/{clase}")
    public String guardar(@PathVariable String clase, @ModelAttribute Productos producto) {
		if (clase.equals("Productos")) {
			productoRepository.save(producto);
	        return "redirect:/listar/Productos/1";
		}else {
	        return "redirect:/index";
	    } 		
    }
	//Eliminar
	@GetMapping("/eliminar/{clase}/{id}")
	public String eliminar(@PathVariable String clase,@PathVariable int id) {
		if (clase.equals("Productos")) {
			productoRepository.deleteById(id);
		    return "redirect:/listar/Productos/1";
		}else {
	        return "redirect:/index";
	    } 		
	}	
	
}
