package ec.edu.espe.productosapirest.service;


import ec.edu.espe.productosapirest.exception.ProductoNoEncontradoException;
import ec.edu.espe.productosapirest.exception.ProductoInvalidoException;
import ec.edu.espe.productosapirest.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    private final List<Producto> productos = new ArrayList<>(List.of(
            new Producto(1L, "Laptop", 850.00, 5L),
            new Producto(2L, "Mouse", 18.50, 4L),
            new Producto(3L, "Teclado", 35.00, 3L)
    ));
    public synchronized List<Producto> listar() {
        return List.copyOf(productos);
    }
    public synchronized Producto buscarPorId(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }
    public synchronized void eliminar(Long id) {
        Producto producto = buscarPorId(id);
        productos.remove(producto);
    }
    public synchronized Producto agregar(Producto producto) {
        if (producto.getId() == null || producto.getId() <= 0) {
            throw new ProductoInvalidoException("El id debe ser positivo");
        }
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new ProductoInvalidoException("El nombre es obligatorio");
        }
        if (!Double.isFinite(producto.getPrecio()) || producto.getPrecio() <= 0) {
            throw new ProductoInvalidoException("El precio debe ser mayor que cero");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new ProductoInvalidoException("El stock no puede ser negativo ni nulo");
        }
        if (productos.stream().anyMatch(p -> p.getId().equals(producto.getId()))) {
            throw new ProductoInvalidoException("Ya existe un producto con ese id");
        }
        productos.add(producto);
        return producto;
    }
}

