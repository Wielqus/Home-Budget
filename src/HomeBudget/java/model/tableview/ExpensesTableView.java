package HomeBudget.java.model.tableview;

import HomeBudget.java.model.Expenses;
import HomeBudget.java.model.Session;
import java.util.Date;
import java.util.Iterator;

/**
 * 
 * @author Wielq
 */
public class ExpensesTableView extends Table {
    private final int userid =  Integer.parseInt(Session.getCurrentSession().get("user"));
    public ExpensesTableView() {
        super();
        Column name = new Column("Nazwa");
        name.addInputFilter("e.name", this);
        Column category = new Column("Kategoria");
        Column person = new Column("Osoba");
        Column date = new Column("Data");
        Column action = new Column("Akcje");
        super.setColumns(name, category, person, date, action);
        this.pagination.setItemsPerPage(this.limit);
    }
    @Override
    public void load() {
        super.clean();      
        
        int numbers = (int)Expenses.getCount(userid, filters);
        this.pagination.setItemsMax(numbers);
        pagination.generatePagination(this);
        
        Iterator list = Expenses.getExpenses(userid, limit, filters,this.pagination.getPage()).iterator();      
        if (list != null) {
            while (list.hasNext()) {
                Object[] expense = (Object[]) list.next();
                String Ename = (String) expense[1];
                Integer Ecategory = (Integer) expense[2];
                Integer Eperson = (Integer) expense[3];
                Date Edate = (Date) expense[4];

                Cell Cname = new Cell();
                Cname.SimpleLabelCell(Ename);

                Cell Ccategory = new Cell();
                Ccategory.SimpleLabelCell(Ecategory.toString());

                Cell Cperson = new Cell();
                Cperson.SimpleLabelCell(Eperson.toString());

                Cell Cdate = new Cell();
                Cdate.SimpleLabelCell(Edate.toString());

                Cell Caction = new Cell();
                Caction.SimpleLabelCell("AKCJE tu beda jakies");

                super.addRow(Cname, Ccategory, Cperson, Cdate, Caction);
            }
        }  
    }

}
