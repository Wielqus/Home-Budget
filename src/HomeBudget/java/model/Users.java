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

    public static  int Login(String login, String password) {
        final org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        List results = null;
        try {
            session.beginTransaction();
             results = session.createCriteria(Users.class)
                     .add(Restrictions.eq( "login", login ))
                     .add(Restrictions.eq("password", password))
                     .list();
            if (results.isEmpty()) {
                return 0;
            }
            Users user = (Users) results.iterator().next();
            return user.getId();
        } catch (HibernateException e) {
            System.err.println(e);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {

        }
        return 0;
    }

    public static boolean Register(String login, String password) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();
            
            String hql = "SELECT user.id FROM Users user WHERE user.login=:login";
            List<Integer> results = session.createQuery(hql)
                    .setString("login", login)
                    .setMaxResults(1)
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
