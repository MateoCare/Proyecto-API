package com.uade.api.ecommerce.ecommerce.util;

import com.uade.api.ecommerce.ecommerce.exceptions.PaginaFueraDelLimiteException;

import java.util.List;

public class PaginationUtils {
    public static <T> List<T> paginar(List<T> items, int page, int rowsPerPage) throws PaginaFueraDelLimiteException {
        int totalItems = items.size();
        if (totalItems == 0 && page == 1) {
            return items;
        }

        int lastPage = totalItems / rowsPerPage + (totalItems % rowsPerPage > 0 ? 1 : 0);
        if (page > lastPage) {
            throw new PaginaFueraDelLimiteException(page);
        }

        int firstIndex = (page - 1) * rowsPerPage;
        int lastIndex = Math.min(firstIndex + rowsPerPage, totalItems);

        return items.subList(firstIndex, lastIndex);
    }
}
