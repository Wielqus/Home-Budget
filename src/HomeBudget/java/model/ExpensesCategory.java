package HomeBudget.java.model;

import HomeBudget.java.hibernate.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.HibernateException;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Wielq
 */
@Entity
@Table(name = "ExpensesCategory")
public class ExpensesCategory implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * It Returns list of expenses category
     *
     * @return List
     */
    public static synchronized List getExpensesCategory() {
        final org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        List results = null;
        try {
            session.beginTransaction();
            results = session.createCriteria(ExpensesCategory.class).list();
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
}
