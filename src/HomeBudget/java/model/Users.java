package HomeBudget.java.model;

import HomeBudget.java.hibernate.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Wielq
 */
public class Users implements Serializable {

    int id;
    String login;
    String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param login
     * @param password
     * @return
     */
    public static  Users Login(String login, String password) {
        final org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
             List results = session.createCriteria(Users.class)
                     .add(Restrictions.eq( "login", login ))
                     .add(Restrictions.eq("password", password))
                     .list();
             if(results.isEmpty()){
                 return null;
             }
            return (Users)results.get(0);
        } catch (HibernateException e) {
            System.err.println(e);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {

        }
        return null;
    }

    /**
     *
     * @param login
     * @param password
     * @return
     */
    public static boolean Register(String login, String password) {
        final org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        List results = null;
        try {
            session.beginTransaction();
            
            results = session.createCriteria(Users.class)
                      .add(Restrictions.eq( "login", login ))
                        .list();
            if (!results.isEmpty()) {
                return false;
            }
            Users user = new Users();
            user.login = login;
            user.password = password;
            
            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            System.err.println(e);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {

        }
        return false;
    }

}
