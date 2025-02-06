package com.example.dashboard.controller;

import com.example.dashboard.entity.Product;
import com.example.dashboard.helper.excelHelper;
import com.example.dashboard.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class productController {
    @Autowired
    private productService pservice;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (excelHelper.checkExcelFormat(file)) {
            try {
                pservice.save(file);
                return ResponseEntity.ok("File uploaded and data saved successfully.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the file.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel file.");
        }
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = pservice.getAllProducts();
        return ResponseEntity.ok(products);
    }
}