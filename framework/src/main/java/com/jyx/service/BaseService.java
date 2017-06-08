package com.jyx.service;

import com.jyx.util.jpa.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T> {

    void save(T entity) throws Exception;

    void delete(List<T> entities) throws Exception;

    Page<T> findEntityPage(T searchEntity, Pageable page, List<SearchFilter> filters) throws Exception;
}
