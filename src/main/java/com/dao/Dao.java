package com.dao;

import java.util.List;

public interface Dao<T> {
    public long insert(String sql,Object...args);
    public void update(String sql,Object...args);
    public T query(String sql,Object...args);
    public List<T> queryForList(String sql, Object...args);
    public <V> V getSingleVal(String sql,Object...args);
    public void batch(String sql,Object[]...params);
}
