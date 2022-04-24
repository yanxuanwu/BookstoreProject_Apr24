package com.dao;

import com.db.JDBCUtils;
import com.utils.ConnectionContext;
import com.utils.ReflectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class BaseDAO<T> implements Dao<T>{
    private QueryRunner queryRunner= new QueryRunner();
    private  Class<T> clazz;
    public BaseDAO(){
        clazz= ReflectionUtils.getSuperGenericType(getClass());
    }


    @Override
    public long insert(String sql, Object... args) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        long id=0;

        try {
            connection= ConnectionContext.getInstance().get();
            preparedStatement= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(args!=null){
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i+1,args[i]);
                }
            }
            preparedStatement.executeUpdate();
            //获取生成主键值
            resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id=resultSet.getLong(1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.release(resultSet,preparedStatement);

        }
        return id;
    }

    @Override
    public void update(String sql, Object... args) {
        Connection connection=null;
        try {
            connection= ConnectionContext.getInstance().get();
            queryRunner.update(connection,sql,args);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public T query(String sql, Object... args) {
        Connection connection=null;
        try {
            connection= ConnectionContext.getInstance().get();
            return  queryRunner.query(connection,sql,new BeanHandler<>(clazz),args);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> queryForList(String sql, Object... args) {
        Connection connection=null;
        try {
            connection= ConnectionContext.getInstance().get();
            return (List<T>) queryRunner.query(connection,sql,new BeanListHandler<>(clazz),args);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <V> V getSingleVal(String sql, Object... args) {
        Connection connection=null;
        try {
            connection= ConnectionContext.getInstance().get();
            return (V) queryRunner.query(connection,sql,new ScalarHandler(),args);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void batch(String sql, Object[]... params) {
        Connection connection=null;
        try {
            connection= ConnectionContext.getInstance().get();
            queryRunner.batch(connection,sql,params);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
