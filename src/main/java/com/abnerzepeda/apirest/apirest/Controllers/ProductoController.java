package com.abnerzepeda.apirest.apirest.Controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


import com.abnerzepeda.apirest.apirest.Repositories.ProductoRepository;
import com.abnerzepeda.apirest.apirest.Entities.Producto;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository ProductoRepository;

    @GetMapping
    public List<Producto> obtenerProductos(){
        return ProductoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return ProductoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producton con el ID: " + id));

    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return ProductoRepository.save(producto);
    }
    
    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto){
        Producto producto = ProductoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: "+ id)); //busca el producto, pero si no lo encuentra lanza un error

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return ProductoRepository.save(producto);
    }
    
    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id){
        Producto producto = ProductoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: "+ id));

        ProductoRepository.delete(producto);
        return "El producto con el ID: " + id + " fue eliminado correctamente";
    }

}

