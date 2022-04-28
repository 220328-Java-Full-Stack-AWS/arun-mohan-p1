package com.revature.persistence;

import com.revature.models.Model;

import java.util.List;

// interface to be inherited by DAOs that implement CRUD methods, use generic T Model so all DAOs can use
public interface CRUDInterface<T extends Model>{

    public T create(T model);
    public T read(int id);
    public void update(T model);
    public void delete(int id);
    public void delete(T model);
    public List<T> getAll();

}
