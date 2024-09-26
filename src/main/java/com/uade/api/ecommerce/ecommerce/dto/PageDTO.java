package com.uade.api.ecommerce.ecommerce.dto;

import lombok.*;

import java.util.List;

@Data
public class PageDTO<T> {
    private List<T> pageItems;
    private int currentPage;
    private int lastPage;
    private int rowsPerPage;
}
