package HomeBudget.java.model;

import HomeBudget.java.hibernate.util.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wielq
 */

public class Expenses implements Serializable {

    private int id;
    private int id_main;
    private String name;
    private int person;
    private int category;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_main() {
        return id_main;
    }

    public void setId_main(int id_main) {
        this.id_main = id_main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * It Returns list of expenses,which belongs to user with
     * @param user id user
     * @param limit max numbers of expenses
     * @param filters 
     * @param page 
     * @return List
     */
    public static List getExpenses(int user, int limit, String filters, int page) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        List results = null;
        try {
            session.beginTransaction();
            String hql = "SELECT e.id,e.name,e.category,e.person,e.date FROM Expenses e  WHERE e.id_main=:user "+ filters + " ORDER BY e.date DESC";
            results = session.createQuery(hql)
                    .setParameter("user", user)
                    .setMaxResults(10)
                    .setFirstResult((page - 1) * limit)
                    .list();
        } catch (HibernateException e) {
            System.err.println(e);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return results;
    }
    /**
     * It returns number of expenses,which belongs to user
     * @param user id user
     * @param filters
     * @return 
     */
    public static long getCount(int user, String filters) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        long count = 0;
        try {
            session.beginTransaction();
            String hql = "SELECT count(*) FROM Expenses e WHERE e.id_main=:user  " + filters;
            count = (long) session.createQuery(hql)
                    .setParameter("user", user)
                    .uniqueResult();
        } catch (HibernateException e) {
            System.err.println(e);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return count;
    }
}
