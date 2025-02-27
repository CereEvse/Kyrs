package ru.cereevse.kyrs.servive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cereevse.kyrs.exceptions.ExceptionHandler;
import ru.cereevse.kyrs.model.Product;
import ru.cereevse.kyrs.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ExceptionHandler exceptionHandler;

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> putProductById(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();
            if (updatedProduct.getProductName() != null) {
                productToUpdate.setProductName(updatedProduct.getProductName());
            }
            if (updatedProduct.getDiscount() != null) {
                productToUpdate.setDiscount(updatedProduct.getDiscount());
            }
            if (updatedProduct.getPrice() != null) {
                productToUpdate.setPrice(updatedProduct.getPrice());
            }
            if (updatedProduct.getCountAvailable() != null) {
                productToUpdate.setCountAvailable(updatedProduct.getCountAvailable());
            }
            productRepository.save(productToUpdate);
        }
        return existingProduct;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
