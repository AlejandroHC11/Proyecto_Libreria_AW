package org.cibertec.edu.pe.modelo.controller;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.cibertec.edu.pe.modelo.Categorias;
import org.cibertec.edu.pe.modelo.Estados;
import org.cibertec.edu.pe.modelo.Productos;
import org.cibertec.edu.pe.modelo.Tipos;
import org.cibertec.edu.pe.modelo.Usuarios;
import org.cibertec.edu.pe.modelo.DetalleBoleta;
import org.cibertec.edu.pe.modelo.Boletas;
import org.cibertec.edu.pe.repository.IBoletaRepository;
import org.cibertec.edu.pe.repository.ICategoriasRepository;
import org.cibertec.edu.pe.repository.IEstadosRepository;
import org.cibertec.edu.pe.repository.IProductosRepository;
import org.cibertec.edu.pe.repository.ITiposRepository;
import org.cibertec.edu.pe.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@SessionAttributes({"carrito","total"})
public class LibreriaController {
	// Inicializacion del objeto carrito
	@ModelAttribute("carrito")
	public List<DetalleBoleta> getCarrito(){
		return new ArrayList<>();
	}
	// Inicializacion del objeto total
	@ModelAttribute("total")
	public BigDecimal getTotal() {
		return new BigDecimal(0);
	}
	
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

	@Autowired
	private IBoletaRepository boletaRepository;
	
	//Index redireccion
	@GetMapping("/inicio")
	public String inicio(HttpSession session) {
		String nombreUsuario = (String) session.getAttribute("nombreUsuario");
	    if (nombreUsuario == null) {
	        return "redirect:/login";
	    }
	    return "inicio";
	}
	@GetMapping("/")
    public String inicioRed() {
        return "redirect:/login";
    }
	@GetMapping("/contactenos")
	public String contactenos() {	    
	    return "contactenos";
	}
	@GetMapping("/tienda")
	public String tienda(Model model,HttpSession session) {
		String nombreUsuario = (String) session.getAttribute("nombreUsuario");
	    if (nombreUsuario == null) {
	        return "redirect:/login";
	    }
		List<Productos> productosList = productoRepository.findAll();
        model.addAttribute("productos", productosList);
	    return "tienda";
	}
	@GetMapping("/carrito")
	public String carrito(HttpSession session) {
		String nombreUsuario = (String) session.getAttribute("nombreUsuario");
	    if (nombreUsuario == null) {
	        return "redirect:/login";
	    }
	    return "carrito";
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
	//Reportes
	@GetMapping("/reporteLibros")
	public String reporteLibros(Model model) {
		try {
            // Cargar el informe JRXML y compilarlo
            ClassPathResource resource = new ClassPathResource("static/reporteLibros.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());

            // Obtener datos
            List<Productos> productos = productoRepository.findAll();

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productos);

            // Parametros para el informe
            Map<String, Object> params = new HashMap<>();

            // Generar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            // Exportar el informe a bytes en PDF
            byte[] informeBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            // Convertir los bytes del informe a Base64
            String base64Encoded = java.util.Base64.getEncoder().encodeToString(informeBytes);

            // Agregar el informe codificado en Base64 al modelo
            model.addAttribute("pdfContent", base64Encoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "reporteLibros";
	}
	@GetMapping("/reporteLibrosXCategoria")
	public String reporteLibrosXCategoria(@RequestParam(name = "categoria", required = false) Integer categoriaId, Model model) {
	    List<Categorias> categoriasList = categoriaRepository.findAll();
	    model.addAttribute("categorias", categoriasList);
	    if (categoriaId == null) {
	        return "reporteLibrosXCategoria";
	    }
	    try {
	    	// Cargar el informe JRXML y compilarlo
	        ClassPathResource resource = new ClassPathResource("static/reporteLibros.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());
	        // Obtener datos
	        List<Productos> productos = productoRepository.findByIdCategoria(categoriaId);

	        if (productos.isEmpty()) {
	            return "noDataFound"; 
	        }
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productos);
	        // Parametros para el informe
	        Map<String, Object> params = new HashMap<>();
	    	// Generar el informe
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
	        // Exportar el informe a bytes en PDF
	        byte[] informeBytes = JasperExportManager.exportReportToPdf(jasperPrint);
	        // Convertir los bytes del informe a Base64
	        String base64Encoded = java.util.Base64.getEncoder().encodeToString(informeBytes);
	        // Agregar el informe codificado en Base64 al modelo
	        model.addAttribute("pdfContent", base64Encoded);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "reporteLibrosXCategoria";
	    }

	    return "reporteLibrosXCategoria";
	}
	//Agregar
	@GetMapping("/agregar/{idprod}")
	public String agregar(Model model,@PathVariable(name="idprod",required = true) int idprod,HttpSession session) {
		Productos producto = productoRepository.findById(idprod).orElse(null);
		List<DetalleBoleta> carrito = (List<DetalleBoleta>)model.getAttribute("carrito");
		if (carrito == null) {
		        carrito = new ArrayList<>();
		}
		BigDecimal total = new BigDecimal(0);
		boolean existe = false;
		DetalleBoleta detalle = new DetalleBoleta();
		LocalDate fechaActual = LocalDate.now();	
		if(producto != null) {
			detalle.setProducto(producto);
			detalle.setCantidad(1);
			detalle.setPrecioVenta(producto.getPrecio());
		}
		// Si el carrito esta vacio
		if(carrito.size() == 0) {
			carrito.add(detalle);
		}else {
				for(DetalleBoleta d : carrito) {
					if(d.getProducto().getIdprod() == producto.getIdprod()) {
						d.setCantidad(d.getCantidad() + 1);
						// Obtener el precio y la cantidad
				        BigDecimal precioProducto = d.getProducto().getPrecio();
				        BigDecimal cantidad = new BigDecimal(d.getCantidad());
				        BigDecimal precioVenta = precioProducto.multiply(cantidad);
						d.setPrecioVenta(precioVenta);
						existe = true;
					}
				}
				if(!existe)carrito.add(detalle);
		}
		// Calculando la suma de sub-totales
		for (DetalleBoleta d : carrito) {			
		    total = total.add(d.getPrecioVenta());
		}
		// Guardar valores en la sesion y pasarlos a la vista
		model.addAttribute("total", total);
		model.addAttribute("carrito", carrito);
		return "redirect:/carrito";
	}
	//Quitar
	@GetMapping("/quitar/{idprod}")
	public String quitar(Model model, @PathVariable(name = "idprod", required = true) int idprod, HttpSession session) {
	    List<DetalleBoleta> carrito = (List<DetalleBoleta>) model.getAttribute("carrito");
	    if (carrito != null) {
	        // Buscar el producto con el id proporcionado en el carrito
	        Optional<DetalleBoleta> optionalDetalle = carrito.stream()
	                .filter(detalle -> detalle.getProducto().getIdprod() == idprod)
	                .findFirst();
	        if (optionalDetalle.isPresent()) {
	            DetalleBoleta detalle = optionalDetalle.get();	            
	            // Si hay mas de 1 producto de este tipo, reducir la cantidad y recalcular el precio
	            if (detalle.getCantidad() > 1) {
	                detalle.setCantidad(detalle.getCantidad() - 1);
	                BigDecimal precioProducto = detalle.getProducto().getPrecio();
	                BigDecimal cantidad = new BigDecimal(detalle.getCantidad());
	                BigDecimal precioVenta = precioProducto.multiply(cantidad);
	                detalle.setPrecioVenta(precioVenta);
	            } else {
	                // Si queda 1 producto, eliminarlo del carrito
	                carrito.remove(detalle);
	            }
	            // Recalcular el total
	            BigDecimal total = new BigDecimal(0);
	            for (DetalleBoleta d : carrito) {
	                total = total.add(d.getPrecioVenta());
	            }
	            // Actualizar los valores en la sesion
	            model.addAttribute("total", total);
	            model.addAttribute("carrito", carrito);
	        }
	    }
	    return "redirect:/carrito";
	}
	//Pagar
	@GetMapping("/pagar")
	public String pagar(Model model, HttpSession session) {
	    List<DetalleBoleta> carrito = (List<DetalleBoleta>) session.getAttribute("carrito");
	    BigDecimal total = (BigDecimal) model.getAttribute("total");
	    LocalDate tiempoActual = LocalDate.now();
	    //Usuario
	    String nombreUsuario = (String) session.getAttribute("nombreUsuario");
	    List<Usuarios> usuariosList = usuarioRepository.findByUsuario(nombreUsuario);
	    Usuarios usuario = usuariosList.isEmpty() ? null : usuariosList.get(0);
	    //Guardar
	    Boletas boleta = new Boletas(usuario,tiempoActual,total);
	    boletaRepository.save(boleta);
	    
	    //Limpiar
	    List<DetalleBoleta> carritoL = new ArrayList<>();
	    model.addAttribute("total", BigDecimal.ZERO);
	    model.addAttribute("carrito", carritoL);
	    return "redirect:/carrito";
	}
}
