package com.uade.api.ecommerce.ecommerce.util;

import com.uade.api.ecommerce.ecommerce.dto.PageDTO;
import com.uade.api.ecommerce.ecommerce.exceptions.PaginaFueraDelLimiteException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public class PaginationUtils {
    public static <T> PageDTO<T> paginar(List<T> items, int page, int rowsPerPage) throws PaginaFueraDelLimiteException {
        PageDTO<T> pageDTO = new PageDTO<T>();
        pageDTO.setCurrentPage(page);
        pageDTO.setRowsPerPage(rowsPerPage);

        int totalItems = items.size();
        if (totalItems == 0 && page == 1) {
            pageDTO.setLastPage(1);
            pageDTO.setPageItems(items);
            return pageDTO;
        }

        int lastPage = totalItems / rowsPerPage + (totalItems % rowsPerPage > 0 ? 1 : 0);
        if (page > lastPage) {
            throw new PaginaFueraDelLimiteException(page);
        }

        int firstIndex = (page - 1) * rowsPerPage;
        int lastIndex = Math.min(firstIndex + rowsPerPage, totalItems);

        pageDTO.setLastPage(lastPage);
        pageDTO.setPageItems(items.subList(firstIndex, lastIndex));
        return pageDTO;
    }

    public static <T, DTO> PageDTO<DTO> toPageDTO(Page<T> page, Function<T, DTO> mapper) throws PaginaFueraDelLimiteException {
        PageDTO<DTO> pageDTO = new PageDTO<>();

        int lastPage = page.getTotalPages() - 1;
        if (lastPage < 0) {
            lastPage = 0;
        }

        pageDTO.setCurrentPage(page.getNumber());
        pageDTO.setLastPage(lastPage);
        pageDTO.setPageItems(page.stream().map(mapper).toList());
        pageDTO.setRowsPerPage(page.getSize());

        if (pageDTO.getCurrentPage() > pageDTO.getLastPage()) {
            throw new PaginaFueraDelLimiteException(pageDTO);
        }

        return pageDTO;
    }
}
