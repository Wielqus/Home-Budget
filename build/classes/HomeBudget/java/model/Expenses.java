package HomeBudget.java.model;

import HomeBudget.java.hibernate.util.HibernateUtil;
import HomeBudget.java.model.tableview.Filter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wielq
 */
@Entity
@Table(name = "Expenses")

public class Expenses implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "id_main")
    private int id_main;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private ExpensesCategory category;
    @Column(name = "Date")
    private java.sql.Date date;

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

    public ExpensesCategory getCategory() {
        return category;
    }

    public void setCategory(ExpensesCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    /**
     * It Returns list of expenses,which belongs to user with
     *
     * @param user id user
     * @param limit max numbers of expenses
     * @param filtersList
     * @param page
     * @return List
     */
    public static List getExpenses(int user, int limit, ArrayList<ArrayList<Filter>> filtersList, int page) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        List results = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Expenses.class);
            criteria.add(Restrictions.eq("id_main", user));
            criteria=Filter.implmentFilters(criteria, filtersList);
            results = criteria.setFirstResult((page - 1) * limit)
                    .setMaxResults(limit)
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
     *
     * @param user id user
     * @param filtersList
     * @return
     */
    public static synchronized long getCount(int user, ArrayList<ArrayList<Filter>> filtersList) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        long count = 0;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Expenses.class);
            criteria.add(Restrictions.eq("id_main", user));
            Filter.implmentFilters(criteria, filtersList);
            count = (long)criteria.setProjection(Projections.rowCount())
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

    /**
     *
     * @param user
     * @param filtersList
     * @return Double Incomes-Expenses for some user
     */
    public static synchronized  double getSum(int user, ArrayList<ArrayList<Filter>> filtersList) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Double sum = 0.0;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Expenses.class);
            criteria.add(Restrictions.eq("id_main", user));
            Filter.implmentFilters(criteria, filtersList);
            sum = (Double)criteria.setProjection(Projections.sum("price"))
                    .uniqueResult();
        } catch (HibernateException e) {
            System.err.println(e);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return  sum==null ? 0:sum;
    }

    /**
     *
     * @param user
     * @param filtersList
     * @return List
     */
    public static synchronized List getPercentCategoriesPart(int user, ArrayList<ArrayList<Filter>> filtersList){
        final Session session = HibernateUtil.getSessionFactory().openSession();
        List results = null;
        try {
            session.beginTransaction();
            results = session.createSQLQuery("SELECT (SUM(e.price)/(SELECT SUM(price) FROM Expenses WHERE id_main="+user+"))*100,c.name FROM Expenses as e JOIN ExpensesCategory AS c ON e.category=c.id WHERE id_main="+user+" GROUP BY category").list();

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
     *
     * @param user
     * @param filtersList
     * @param date
     * @return List Incomes-Expenses for some user to some date
     */
    public static synchronized List getResultToDay(int user, ArrayList<ArrayList<Filter>> filtersList,java.sql.Date date){
        final Session session = HibernateUtil.getSessionFactory().openSession();
        List results = null;
        try {
            session.beginTransaction();
            String sql = "SELECT ((SELECT COALESCE(SUM(i.price),0) FROM Incomes AS i WHERE i.id_main="+user+" AND i.date<'"+date.toString()+"') -  (SELECT COALESCE(SUM(e.price),0) FROM Expenses AS e WHERE e.id_main="+user+" AND e.date<'"+date.toString()+"')) FROM Expenses WHERE 1 LIMIT 1";
            results = session.createSQLQuery(sql).list();

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
     *
     * @param user
     * @param filtersList
     * @param date
     * @return List Incomes-Expenses for some user for some date
     */
    public static synchronized List getResultFromDay(int user, ArrayList<ArrayList<Filter>> filtersList,java.sql.Date date){
        final Session session = HibernateUtil.getSessionFactory().openSession();
        List results = null;
        try {
            session.beginTransaction();
            String sql ="SELECT ((SELECT COALESCE(SUM(i.price),0) FROM Incomes AS i WHERE i.id_main="+user+" AND i.date='"+date.toString()+"') -  (SELECT COALESCE(SUM(e.price),0) FROM Expenses AS e WHERE e.id_main="+user+" AND e.date='"+date.toString()+"')) FROM Expenses WHERE 1 LIMIT 1";
            results = session.createSQLQuery(sql).list();

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
     *
     * @param idUser
     * @param name
     * @param price
     * @param category
     * @param date
     */
    public static void insertExpense(int idUser, String name, double price, int category, java.sql.Date date) {

        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Expenses expense = new Expenses();
            expense.setId_main(idUser);
            expense.setName(name);
            expense.setPrice(price);
            expense.setCategory((ExpensesCategory) session.get(ExpensesCategory.class, category));
            expense.setDate(date);

            session.save(expense);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(e);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }

    }
    /**
     *
     * @param idUser
     * @param name
     * @param price
     * @param category
     * @param date
     */
    public static void editExpense(int idExpense, int idUser, String name, double price, int category, java.sql.Date date) {

        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Expenses expense = (Expenses) session.get(Expenses.class, idExpense);
            expense.setId_main(idUser);
            expense.setName(name);
            expense.setPrice(price);
            expense.setCategory((ExpensesCategory) session.get(ExpensesCategory.class, category));
            expense.setDate(date);

            session.save(expense);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(e);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }

    }

    /**
     *
     * @param id
     */
    public static void deleteExpense(int id) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Expenses expense = (Expenses) session.get(Expenses.class, id);

            session.delete(expense);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(e);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }
    
    

}
