package com.bugztracker.persistence.dao;

import java.util.List;

/**
 * Created by Oleh_Osyka for EPMC-CLO
 * Date: 13.02.2016
 * Time: 17:53
 */
public interface IBaseDao<ENTITY> {

    List<ENTITY> getAll();

    List<ENTITY> findAll(int skip, int limit);

    ENTITY get(String id);

    void add(ENTITY entity);

    void delete(ENTITY entity);

    void update(ENTITY entity);

}
