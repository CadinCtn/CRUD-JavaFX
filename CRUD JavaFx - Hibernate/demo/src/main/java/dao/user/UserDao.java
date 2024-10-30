package dao.user;

import connectionFactory.SimpleEntityManager;
import dao.GenericDao;
import model.User;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDao extends GenericDao<User> {

    private SimpleEntityManager simpleEntityManager;
    public UserDao(SimpleEntityManager simpleEntityManager) {
        super(User.class, simpleEntityManager);
        this.simpleEntityManager = simpleEntityManager;
    }

    //Função para efetuar login
    public User login(String login, String password) throws NoResultException {
        try{
            simpleEntityManager.beginTransaction(); // Inicia conexão

            //Criando consulta
            CriteriaBuilder builder = simpleEntityManager.getEntityManager().getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);

            //Condições
            query.select(root).where(
              builder.and(
                      builder.equal(root.get("login"), login),
                      builder.equal(root.get("password"), password)
              )
            );

            //Retorna o usuário
            return simpleEntityManager.getEntityManager().createQuery(query).getSingleResult();

        }finally {
            simpleEntityManager.close(); //Fecha conexão
        }
    }

}
