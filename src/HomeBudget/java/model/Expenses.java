package HomeBudget.java.model;

import HomeBudget.java.hibernate.util.HibernateUtil;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @Column(name="Date")
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
        this.category =  category;
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
     * @param filters
     * @param page
     * @return List
     */
    public static  List getExpenses(int user, int limit, String filters, int page) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        List results = null;
        try {
            session.beginTransaction();
            results = session.createCriteria(Expenses.class)
                    .setFirstResult((page-1)*limit)
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
     * @param filters
     * @return
     */
    public static synchronized long getCount(int user, String filters) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        long count = 0;
        try {
            session.beginTransaction();
            count = (long)session.createCriteria(Expenses.class)
                    .setProjection(Projections.rowCount())
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
    
    public static void insertExpense(int idUser, String name, double price, int category, java.sql.Date date) {

        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Expenses expense = new Expenses();
            expense.setId_main(idUser);
            expense.setName(name);
            expense.setPrice(price);
            //expense.setCategory(category);
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
    
    public static void editExpense(int idExpense,int idUser, String name, double price, int category, java.sql.Date date) {

        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Expenses expense = (Expenses) session.get(Expenses.class,idExpense);
            expense.setId_main(idUser);
            expense.setName(name);
            expense.setPrice(price);
            //expense.setCategory(category);
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
    
    public static void deleteExpense(int id){
         final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Expenses expense = (Expenses) session.get(Expenses.class,id);

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
