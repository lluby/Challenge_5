package com.example.Challenge_5.controller;

import com.example.Challenge_5.model.Product;
import com.example.Challenge_5.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/api/product")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(produces = "application/json")
    @Operation(summary = "Api to get all product")
    public List<Product> getProduct() {
        return productService.getAllProduct();
    }

    @PostMapping(value = "add-product")
    public ResponseEntity addProduct(@RequestBody Product product){
        productService.addProduct(product.builder()
                        .ProductCode(product.getProductCode())
                        .ProductName(product.getProductName())
                        .price(product.getPrice())
                        .merchant(product.getMerchant())
                .build());
        return ResponseEntity.ok("Product added successfully");
    }
    @PostMapping(value = "update-product")
    public ResponseEntity updateProduct(@RequestBody Product product) {
        Product updateProduct = productService.updateProduct(product);
        if (updateProduct != null) {
            return new ResponseEntity<>(updateProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to update Product", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{ProductCode}")
    public String deleteProduct(@PathVariable("ProductCode") String ProductCode) {
        productService.deleteProduct(ProductCode);
        return "Delete Product " + ProductCode+ " success!";
    }
    @GetMapping("/detail/{ProductCode}")
    public ResponseEntity getProductDetail(@PathVariable String ProductCode) {
        Product product = productService.getProductDetails(ProductCode);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/detail")
    @Operation(summary = "Getting detail of one product by product code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Inputted product code not found")
    })

    private List<?> testWildCard(){
        return Arrays.asList("String", 40);
    }


}
