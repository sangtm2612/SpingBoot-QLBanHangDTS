package com.example.sangtmph17730_asm.services;

import com.example.sangtmph17730_asm.entities.Category;
import com.example.sangtmph17730_asm.repository.CategoryRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CategoryServiceImp implements CategoryService{

    CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findAll(Sort sort) {
        return categoryRepository.findAll(sort);
    }

    public List<Category> findAllById(Iterable<Integer> integers) {
        return categoryRepository.findAllById(integers);
    }

    public <S extends Category> List<S> saveAll(Iterable<S> entities) {
        return categoryRepository.saveAll(entities);
    }

    public void flush() {
        categoryRepository.flush();
    }

    public <S extends Category> S saveAndFlush(S entity) {
        return categoryRepository.saveAndFlush(entity);
    }

    public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
        return categoryRepository.saveAllAndFlush(entities);
    }

    @Deprecated
    public void deleteInBatch(Iterable<Category> entities) {
        categoryRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Category> entities) {
        categoryRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        categoryRepository.deleteAllByIdInBatch(integers);
    }

    public void deleteAllInBatch() {
        categoryRepository.deleteAllInBatch();
    }

    @Deprecated
    public Category getOne(Integer integer) {
        return categoryRepository.getOne(integer);
    }

    @Deprecated
    public Category getById(Integer integer) {
        return categoryRepository.getById(integer);
    }

    public Category getReferenceById(Integer integer) {
        return categoryRepository.getReferenceById(integer);
    }

    public <S extends Category> List<S> findAll(Example<S> example) {
        return categoryRepository.findAll(example);
    }

    public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
        return categoryRepository.findAll(example, sort);
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public <S extends Category> S save(S entity) {
        return categoryRepository.save(entity);
    }

    public Optional<Category> findById(Integer integer) {
        return categoryRepository.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return categoryRepository.existsById(integer);
    }

    public long count() {
        return categoryRepository.count();
    }

    public void deleteById(Integer integer) {
        categoryRepository.deleteById(integer);
    }

    public void delete(Category entity) {
        categoryRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        categoryRepository.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends Category> entities) {
        categoryRepository.deleteAll(entities);
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return categoryRepository.findOne(example);
    }

    public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
        return categoryRepository.findAll(example, pageable);
    }

    public <S extends Category> long count(Example<S> example) {
        return categoryRepository.count(example);
    }

    public <S extends Category> boolean exists(Example<S> example) {
        return categoryRepository.exists(example);
    }

    public <S extends Category, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return categoryRepository.findBy(example, queryFunction);
    }
}
