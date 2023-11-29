package org.cibertec.edu.pe.modelo.controller;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.modelo.Categorias;
import org.cibertec.edu.pe.modelo.Productos;
import org.cibertec.edu.pe.modelo.Usuarios;
import org.cibertec.edu.pe.modelo.Tipos;
import org.cibertec.edu.pe.modelo.Estados;
import org.cibertec.edu.pe.repository.ICategoriasRepository;
import org.cibertec.edu.pe.repository.IEstadosRepository;
import org.cibertec.edu.pe.repository.IProductosRepository;
import org.cibertec.edu.pe.repository.ITiposRepository;
import org.cibertec.edu.pe.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class LibreriaController {
	
	@Autowired
	private IProductosRepository productoRepository;
	
	@Autowired
	private ICategoriasRepository categoriaRepository;
	
	@Autowired
	private IUsuariosRepository usuarioRepository;
	
	@Autowired
	private ITiposRepository tipoRepository;
	
	@Autowired
	private IEstadosRepository estadoRepository;

	//Index redireccion
	@GetMapping("inicio")
	public String index() {	    
	    return "inicio";
	}
	@GetMapping("contactenos")
	public String contactenos() {	    
	    return "contactenos";
	}
	@GetMapping("tienda")
	public String tienda() {	    
	    return "tienda";
	}
	@GetMapping({"/listar/","/listar"})
	public String listarRed() {
		return "redirect:/inicio";
	}
	@GetMapping({"/buscar/","/buscar"})
	public String buscarRed() {
		return "redirect:/inicio";
	}
	@GetMapping({"/nuevo/","/nuevo"})
	public String nuevoRed() {
		return "redirect:/inicio";
	}
	@GetMapping({"/modificar/","/modificar"})
	public String modificarRed() {
		return "redirect:/inicio";
	}
	@GetMapping({"/eliminar/","/eliminar"})
	public String eliminarRed() {
		return "redirect:/inicio";
	}
	//Listar
	@GetMapping("/listar/{clase}/{pagina}")
	public String listar(@PathVariable String clase, Model model,@PathVariable int pagina) {
		int pageSize = 5;
		if (clase.equals("Productos")) {			
			Page<Productos> modelPage = productoRepository.findAll(PageRequest.of(pagina-1, pageSize));
	        List<Productos> modelList = modelPage.getContent();
	        model.addAttribute("productos", modelList);
	        return "./Producto/listarProducto";
	    }else if (clase.equals("Categorias")) {
	    	Page<Categorias> modelPage = categoriaRepository.findAll(PageRequest.of(pagina-1, pageSize));
	        List<Categorias> modelList = modelPage.getContent();
		    model.addAttribute("categorias", modelList);
		    return "./Categoria/listarCategoria";
	    }else if (clase.equals("Usuarios")) {
	    	Page<Usuarios> modelPage = usuarioRepository.findAll(PageRequest.of(pagina-1, pageSize));
	        List<Usuarios> modelList = modelPage.getContent();
		    model.addAttribute("usuarios", modelList);
		    return "./Usuario/listarUsuario";
	    }else if (clase.equals("Tipos")) {
	    	Page<Tipos> modelPage = tipoRepository.findAll(PageRequest.of(pagina-1, pageSize));
	        List<Tipos> modelList = modelPage.getContent();
		    model.addAttribute("tipos", modelList);
		    return "./Tipo/listarTipo";
	    }else if (clase.equals("Estados")) {
	    	Page<Estados> modelPage = estadoRepository.findAll(PageRequest.of(pagina-1, pageSize));
	        List<Estados> modelList = modelPage.getContent();
		    model.addAttribute("estados", modelList);
		    return "./Estado/listarEstado";
	    }else {
	        return "redirect:/inicio";
	    }
	}
	//Buscar
	@GetMapping("/buscarDescripcion/{clase}")
	public String buscarDescripcion(@PathVariable String clase, @RequestParam(name = "descripcion", required = false) String descripcion, Model model) {
		if (clase.equals("Productos")) {
			List<Productos> lista;
		    if (descripcion != null && descripcion.trim().length() > 0) {
		        lista = productoRepository.findByDescripcion(descripcion);
		    } else {
		        lista = productoRepository.findAll();
		    }
		    model.addAttribute("productos", lista);
		    return "./Producto/listarProducto";
		}else if (clase.equals("Categorias")) {
			List<Categorias> lista;
		    if (descripcion != null && descripcion.trim().length() > 0) {
		        lista = categoriaRepository.findByDescripcion(descripcion);
		    } else {
		        lista = categoriaRepository.findAll();
		    }
		    model.addAttribute("categorias", lista);
		    return "./Categoria/listarCategoria";
		}if (clase.equals("Tipos")) {
			List<Tipos> lista;
		    if (descripcion != null && descripcion.trim().length() > 0) {
		        lista = tipoRepository.findByDescripcion(descripcion);
		    } else {
		        lista = tipoRepository.findAll();
		    }
		    model.addAttribute("tipos", lista);
		    return "./Tipo/listarTipo";
		}if (clase.equals("Estados")) {
			List<Estados> lista;
		    if (descripcion != null && descripcion.trim().length() > 0) {
		        lista = estadoRepository.findByDescripcion(descripcion);
		    } else {
		        lista = estadoRepository.findAll();
		    }
		    model.addAttribute("estados", lista);
		    return "./Estado/listarEstado";
		}else {
	        return "redirect:/inicio";
	    }		
	}
	@GetMapping("/buscarUsuario/{clase}")
	public String buscarUsuario(@PathVariable String clase, @RequestParam(name = "usuario", required = false) String usuario, Model model) {
		if (clase.equals("Usuarios")) {
			List<Usuarios> lista;
		    if (usuario != null && usuario.trim().length() > 0) {
		        lista = usuarioRepository.findByUsuario(usuario);
		    } else {
		        lista = usuarioRepository.findAll();
		    }
		    model.addAttribute("usuarios", lista);
		    return "./Usuario/listarUsuario";
		}else {
	        return "redirect:/inicio";
	    }		
	}
	//NuevoProducto
	@GetMapping("/nuevo/{clase}")
    public String nuevo(@PathVariable String clase, Model model) {
		if (clase.equals("Productos")) {
			List<Categorias> categoriasList = categoriaRepository.findAll();
	        model.addAttribute("categorias", categoriasList);
	        Long ultimoId = productoRepository.ultimoId();
	        model.addAttribute("ultimoId", ultimoId+1);
	        return "./Producto/nuevoProducto";
		}else if (clase.equals("Categorias")) {	
			Long ultimoId = categoriaRepository.ultimoId();
	        model.addAttribute("ultimoId", ultimoId+1);
	        return "./Categoria/nuevaCategoria";
		}else if (clase.equals("Usuarios")) {
			List<Tipos> tiposList = tipoRepository.findAll();
	        model.addAttribute("tipos", tiposList);
	        List<Estados> estadosList = estadoRepository.findAll();
	        model.addAttribute("estados", estadosList);
			Long ultimoId = usuarioRepository.ultimoId();
	        model.addAttribute("ultimoId", ultimoId+1);
	        return "./Usuario/nuevoUsuario";
		}else if (clase.equals("Tipos")) {	
			Long ultimoId = tipoRepository.ultimoId();
	        model.addAttribute("ultimoId", ultimoId+1);
	        return "./Tipo/nuevoTipo";
		}else if (clase.equals("Estados")) {	
			Long ultimoId = estadoRepository.ultimoId();
	        model.addAttribute("ultimoId", ultimoId+1);
	        return "./Estado/nuevoEstado";
		}else {
	        return "redirect:/inicio";
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
		        
		        return "./Producto/modificarProducto";
		    } else {
		        return "redirect:/listar/Productos/1";
		    }
		}else if (clase.equals("Categorias")) {
			Optional<Categorias> optionalCategoria = categoriaRepository.findById(id);
		    
		    if (optionalCategoria.isPresent()) {
		        Categorias categoria = optionalCategoria.get();
		        model.addAttribute("categoria", categoria);
		        
		        return "./Categoria/modificarCategoria";
		    } else {
		        return "redirect:/listar/Categorias/1";
		    }
		}else if (clase.equals("Usuarios")) {
			Optional<Usuarios> optionalUsuario = usuarioRepository.findById(id);
		    
		    if (optionalUsuario.isPresent()) {
		        Usuarios usuarios = optionalUsuario.get();
		        model.addAttribute("usuario", usuarios);
		        
		        List<Tipos> tiposList = tipoRepository.findAll();
		        model.addAttribute("tipo", tiposList);
		        
		        List<Estados> estadosList = estadoRepository.findAll();
		        model.addAttribute("estado", estadosList);
		        
		        return "./Usuario/modificarUsuario";
		    } else {
		        return "redirect:/listar/Usuarios/1";
		    }
		}else if (clase.equals("Tipos")) {
			Optional<Tipos> optionalTipo = tipoRepository.findById(id);
		    
		    if (optionalTipo.isPresent()) {
		        Tipos tipos = optionalTipo.get();
		        model.addAttribute("tipo", tipos);
		        
		        return "./Tipo/modificarTipo";
		    } else {
		        return "redirect:/listar/Tipos/1";
		    }
		}else if (clase.equals("Estados")) {
			Optional<Estados> optionalEstado = estadoRepository.findById(id);
		    
		    if (optionalEstado.isPresent()) {
		        Estados estados = optionalEstado.get();
		        model.addAttribute("estado", estados);
		        
		        return "./Estado/modificarEstado";
		    } else {
		        return "redirect:/listar/Estados/1";
		    }
		}else {
	        return "redirect:/inicio";
	    } 		
	}
	//Guardar
	@PostMapping("/guardarProducto")
    public String guardarProducto(@ModelAttribute Productos producto) {
			productoRepository.save(producto);
	        return "redirect:/listar/Productos/1";				
    }
	@PostMapping("/guardarCategoria")
    public String guardarCategoria(@ModelAttribute Categorias categoria) {
			categoriaRepository.save(categoria);
	        return "redirect:/listar/Categorias/1";				
    }
	@PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuarios usuario) {
			usuarioRepository.save(usuario);
	        return "redirect:/listar/Usuarios/1";				
    }
	@PostMapping("/guardarTipo")
    public String guardarTipo(@ModelAttribute Tipos tipo) {
			tipoRepository.save(tipo);
	        return "redirect:/listar/Tipos/1";				
    }
	@PostMapping("/guardarEstado")
    public String guardarEstado(@ModelAttribute Estados estado) {
			estadoRepository.save(estado);
	        return "redirect:/listar/Estados/1";				
    }
	//Eliminar
	@GetMapping("/eliminar/{clase}/{id}")
	public String eliminar(@PathVariable String clase,@PathVariable int id, Model model) {
		if (clase.equals("Productos")) {
			try {
				productoRepository.deleteById(id);
			} catch (DataIntegrityViolationException e){
				System.out.println("No se puede borrar. Producto en uso");
				return "redirect:/listar/Productos/1";
			}
		    return "redirect:/listar/Productos/1";
		}else if (clase.equals("Categorias")) {
			try {
				categoriaRepository.deleteById(id);
			}catch (DataIntegrityViolationException e){
				System.out.println("No se puede borrar. Categoria en uso");
				return "redirect:/listar/Categorias/1";
			}		
		    return "redirect:/listar/Categorias/1";
		}else if (clase.equals("Usuarios")) {
			try {
				usuarioRepository.deleteById(id);
			} catch(DataIntegrityViolationException e) {
				System.out.println("No se puede borrar. Usuario en uso");
				return "redirect:/listar/Usuarios/1";
			}
		    return "redirect:/listar/Usuarios/1";
		}else if (clase.equals("Tipos")) {
			try {
				tipoRepository.deleteById(id);
			}catch(DataIntegrityViolationException e){
				System.out.println("No se puede borrar. Tipo en uso");
				return "redirect:/listar/Tipos/1";
			}
		    return "redirect:/listar/Tipos/1";
		}else if (clase.equals("Estados")) {
			try {
				estadoRepository.deleteById(id);
			}catch(DataIntegrityViolationException e){
				System.out.println("No se puede borrar. Estado en uso");
				return "redirect:/listar/Estados/1";
			}
		    return "redirect:/listar/Estados/1";
		}else {
	        return "redirect:/inicio";
	    } 		
	}	
	
}
