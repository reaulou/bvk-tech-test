//package com.reaulou.bvktechtest.service;
//
//import com.reaulou.bvktechtest.model.Product;
//import com.reaulou.bvktechtest.repository.ProductRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ProductServiceTest {
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @InjectMocks
//    private ProductServiceImpl productService;
//
//    //private Product product;
//
////    private Product buildProduct(long id, String name, Integer price, Integer quantity){
////        Product product1 = new Product();
////        Product product = new Product(name, price, quantity);
////        return product;
////    }
//
//    @BeforeEach
//    public void init(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAddProduct() {
//        Product product = new Product("Buku Player's Handbook DND", 199999, 7);
//
//        productService.addProduct(product);
//
//        // verify that this method was invoked exactly 1 time
//        verify(productRepository, times(1)).save(product);
//    }
//
//    @Test
//    void testGetAllProducts() {
//        List<Product> list = new ArrayList<Product>();
//        Product product1 = new Product("Buku Player's Handbook DND", 199999, 7);
//        Product product2 = new Product("DND Dice Set", 749999, 3);
//        Product product3 = new Product("Penggaris", 23000, 26);
//
//        list.add(product1);
//        list.add(product2);
//        list.add(product3);
//
//        when(productRepository.findAll()).thenReturn(list);
//
//        //test
//        List<Product> empList = productService.getAllProducts();
//
//        assertEquals(3, empList.size());
//        // verify that this method was invoked exactly 1 time
//        verify(productRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testGetProductQuantityById_productExist(){
//        Product product = new Product("Buku Player's Handbook DND", 199999, 7);
//
//        long id = 1;
//        when(productRepository.findById(id)).thenReturn(Optional.of(product));
//
//        // test
//        Integer returnedQuantity = productService.getProductQuantityById(id);
//
//        assertEquals(7, returnedQuantity);
//        // verify that this method was invoked exactly 1 time
//        verify(productRepository, times(1)).findById(id);
//    }
//
//    @Test
//    void testGetProductQuantityById_productNotExist(){
//        long id = 1;
//        when(productRepository.findById(id)).thenReturn(Optional.empty());
//
//        // test
//        Integer returnedQuantity = productService.getProductQuantityById(id);
//
//        assertEquals(null, returnedQuantity);
//        // verify that this method was invoked exactly 1 time
//        verify(productRepository, times(1)).findById(id);
//    }
//
//    @Test
//    void testUpdateProductQuantityById_productExist(){
//        Product product = new Product("Buku Player's Handbook DND", 199999, 7);
//        Product newProduct = new Product("Buku Player's Handbook DND", 199999, 11);
//
//        long id = 1;
//        when(productRepository.findById(any())).thenReturn(Optional.of(product));
//        when(productRepository.save(any(Product.class))).thenReturn(newProduct);
//
//        // test
//        Product returnedProduct = productService.updateProductQuantityById(id, 11);
//
//        assertEquals(11, returnedProduct.getQuantity());
//        // verify that this method was invoked exactly 1 time
//        verify(productRepository, times(1)).findById(id);
//        verify(productRepository, times(1)).save(any(Product.class));
//    }
//
//    @Test
//    void testUpdateProductQuantityById_productNotExist(){
//        long id = 1;
//        when(productRepository.findById(id)).thenReturn(Optional.empty());
//
//        // test
//        Product returnedProduct = productService.updateProductQuantityById(id, 11);
//
//        assertEquals(null, returnedProduct);
//        // verify that this method was invoked exactly 1 time
//        verify(productRepository, times(1)).findById(id);
//    }
//
//    @Test
//    void testExecuteProductOrder_success(){
//        Product product = new Product("Buku Player's Handbook DND", 199999, 7);
//
//        long id = 1;
//        Integer orderedQuantity = 5;
//        when(productRepository.findById(any())).thenReturn(Optional.of(product));
//
//        //test
//        Integer returnedQuantity = productService.executeProductOrder(id, orderedQuantity);
//
//        // should be 2 books left after buying 5 from a stock of 7
//        assertEquals(2, returnedQuantity);
//        // verify that this method was invoked exactly 1 time
//        verify(productRepository, times(1)).findById(id);
//        verify(productRepository, times(1)).save(any(Product.class));
//    }
//
//    @Test
//    void testExecuteProductOrder_notExist(){
//        long id = 1;
//        Integer orderedQuantity = 5;
//        when(productRepository.findById(any())).thenReturn(Optional.empty());
//
//        //test
//        Integer returnedQuantity = productService.executeProductOrder(id, orderedQuantity);
//
//        // should be 2 books left after buying 5 from a stock of 7
//        assertEquals(null, returnedQuantity);
//        // verify that this method was invoked exactly 1 time
//        verify(productRepository, times(1)).findById(id);
//    }
//
//    @Test
//    void testExecuteProductOrder_orderQuantitySameWithStockQuantity(){
//        Product product = new Product("Buku Player's Handbook DND", 199999, 7);
//
//        long id = 1;
//        Integer orderedQuantity = 7;
//        when(productRepository.findById(any())).thenReturn(Optional.of(product));
//
//        //test
//        Integer returnedQuantity = productService.executeProductOrder(id, orderedQuantity);
//
//        // should be 0 books left after buying 7 from a stock of 7
//        assertEquals(0, returnedQuantity);
//        // verify that this method was invoked exactly 1 time
//        verify(productRepository, times(1)).findById(id);
//        verify(productRepository, times(1)).deleteById(id);
//    }
//}
