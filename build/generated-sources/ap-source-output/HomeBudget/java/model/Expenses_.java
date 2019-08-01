package HomeBudget.java.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Expenses.class)
public abstract class Expenses_ {

	public static volatile SingularAttribute<Expenses, Date> date;
	public static volatile SingularAttribute<Expenses, Double> price;
	public static volatile SingularAttribute<Expenses, String> name;
	public static volatile SingularAttribute<Expenses, Integer> id;
	public static volatile SingularAttribute<Expenses, ExpensesCategory> category;
	public static volatile SingularAttribute<Expenses, Integer> id_main;

}

