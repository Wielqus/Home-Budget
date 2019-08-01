package HomeBudget.java.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Incomes.class)
public abstract class Incomes_ {

	public static volatile SingularAttribute<Incomes, Date> date;
	public static volatile SingularAttribute<Incomes, Double> price;
	public static volatile SingularAttribute<Incomes, String> name;
	public static volatile SingularAttribute<Incomes, Integer> id;
	public static volatile SingularAttribute<Incomes, IncomesCategory> category;
	public static volatile SingularAttribute<Incomes, Integer> id_main;

}

