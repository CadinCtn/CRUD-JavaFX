package connectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SimpleEntityManager {

    private EntityManager entityManager;
    private final EntityManagerFactory factory;

    public SimpleEntityManager(){
        //Criando Factory
        factory = Persistence.createEntityManagerFactory("Persistence");
        this.entityManager = factory.createEntityManager();
    }

    public void beginTransaction(){
        //Abrindo entityManager
        if(!entityManager.isOpen()){
            entityManager = factory.createEntityManager();
        }
        //Iniciando transação
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
    }
    public void commit(){
        entityManager.getTransaction().commit();
    }
    public void close(){
        if(entityManager == null) return; // Verifica se não está nulo
        // Se houver uma transação aberta, faz rollback para garantir que a conexão seja liberada
        if(entityManager.getTransaction().isActive()){
            entityManager.getTransaction().rollback();
        }
        entityManager.close(); //Fecha conexão
    }
    public void rollback(){
        entityManager.getTransaction().rollback();
    }
    public EntityManager getEntityManager(){
        return entityManager;
    }
    public void shutdown(){
        if(factory != null){
            factory.close();
        }
        if(entityManager != null){
            entityManager.close();
        }
    }

}
