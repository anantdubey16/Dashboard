package com.example.dashboard.service;

import com.example.dashboard.entity.Product;

import com.example.dashboard.helper.excelHelper;
import com.example.dashboard.repository.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class productService {
    @Autowired
    private productRepo prepo;

    public void save(MultipartFile file) {

        try {
            List<Product> products = excelHelper.convertExcelToListOfProduct(file.getInputStream());
            this.prepo.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Product> getAllProducts() {
        return this.prepo.findAll();
    }

    public Product saveProduct(Product product) {
        return prepo.save(product);
    }
}