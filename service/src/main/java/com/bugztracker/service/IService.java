package com.bugztracker.service;

import java.util.Optional;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
public interface IService<ENTITY> {

    Optional<ENTITY> get(String id);

//    List<ENTITY> getAll();
//
//    void add(ENTITY entity);
//
//    void delete(ENTITY entity);
//
//    void update(ENTITY entity);
}
