package dao;

import connectionFactory.SimpleEntityManager;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class GenericDao<T> implements Dao<T> {

    private Class<T> tClass;
    private SimpleEntityManager simpleEntityManager;

    public GenericDao(Class<T> tClass, SimpleEntityManager simpleEntityManager) {
        this.tClass = tClass;
        this.simpleEntityManager = simpleEntityManager;
    }

    @Override
    public void create(T t) throws Exception{
        try{
            simpleEntityManager.beginTransaction(); //Inicia transação
            simpleEntityManager.getEntityManager().persist(t); //INSERT
            simpleEntityManager.commit(); //Confirma a transação
        }catch (Exception e){
            simpleEntityManager.rollback(); //Desfaz alterações em caso de erro
            throw e;
        } finally {
            simpleEntityManager.close(); //Fecha a conexão
        }
    }

    @Override
    public T read(int id){
        simpleEntityManager.beginTransaction(); //Inicia transação
        T object = simpleEntityManager.getEntityManager().find(tClass,id); //Busca objeto pelo ID
        simpleEntityManager.close(); // Encerra conexão
        return object;
    }

    @Override
    public List<T> readAll() {
        simpleEntityManager.beginTransaction(); //Inicia transação
        List<T> list = simpleEntityManager.getEntityManager().createQuery("FROM " + tClass.getName()).getResultList();
        simpleEntityManager.close(); // Encerra conexão
        return list;
    }

    @Override
    public void update(T t) throws Exception{
        try{
            simpleEntityManager.beginTransaction(); //Inicia transação
            simpleEntityManager.getEntityManager().merge(t); //UPDATE
            simpleEntityManager.commit(); //Confirma a transação
        }catch (Exception e){
            simpleEntityManager.rollback(); //Desfaz alterações em caso de erro
            throw e;
        } finally {
            simpleEntityManager.close(); //Fecha a conexão
        }
    }

    @Override
    public void delete(int id) throws Exception{
        try{
            simpleEntityManager.beginTransaction(); //Inicia transação
            T t = simpleEntityManager.getEntityManager().find(tClass, id); //Busca objeto
            //Se existir
            if(t != null){
                simpleEntityManager.getEntityManager().remove(t); //DELETE
                simpleEntityManager.commit(); //Confirma a transação
            }
        }catch (Exception e){
            simpleEntityManager.rollback(); //Desfaz alterações em caso de erro
            throw e;
        } finally {
            simpleEntityManager.close(); //Fecha a conexão
        }
    }
}
