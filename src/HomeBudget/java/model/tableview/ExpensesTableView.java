package HomeBudget.java.model.tableview;

import HomeBudget.java.controller.AddexpensesController;
import HomeBudget.java.model.Expenses;
import HomeBudget.java.model.ExpensesCategory;
import HomeBudget.java.model.Navigation;
import HomeBudget.java.model.Session;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * 
 * @author Wielq
 */
public class ExpensesTableView extends Table {
    private final int userid =  Integer.parseInt(Session.getCurrentSession().get("user"));
    public ExpensesTableView() {     
        super();
        Column name = new Column("Nazwa");
        name.addInputFilter("name",this,"Nazwa wydatku...");
        Column category = new Column("Kategoria");      
        Column person = new Column("Kwota");
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
                Expenses expense = (Expenses) list.next();
                String Ename = expense.getName();
                ExpensesCategory Ecategory = expense.getCategory();
                Double Eprice = expense.getPrice();
                java.sql.Date Edate = expense.getDate();

                Cell Cname = new Cell();
                Cname.SimpleLabelCell(Ename);

                Cell Ccategory = new Cell();
                Ccategory.SimpleLabelCell(Ecategory.getName());

                Cell Cperson = new Cell();
                Cperson.SimpleLabelCell(Eprice.toString());

                Cell Cdate = new Cell();
                Cdate.SimpleLabelCell(Edate.toString());

                Cell Caction = new Cell();
                
                Button edit = GlyphsDude.createIconButton(FontAwesomeIcon.EDIT, "", "15px", "15px", ContentDisplay.GRAPHIC_ONLY);
                edit.getStyleClass().add("yellow");
                edit.setOnAction(e->{
                    this.edit(expense.getId(),Ename,Ecategory.getId(),Eprice,Edate);
                });
                Caction.addRowButton(edit);
                
                Button delete = GlyphsDude.createIconButton(FontAwesomeIcon.REMOVE, "", "15px", "15px", ContentDisplay.GRAPHIC_ONLY);
                delete.getStyleClass().add("negative");
                delete.setOnAction(e->{
                    this.delete(expense.getId());
                });
                Caction.addRowButton(delete);
                
                
                

                super.addRow(Cname, Ccategory, Cperson, Cdate, Caction);
            }
        }  
    }
    
    private void edit(int id,String name,int category,double price,java.sql.Date date){
        AddexpensesController controller = (AddexpensesController) Navigation.getNavigation().openModal(AddexpensesController.URL_FXML, this);
        controller.setForm(id, name, price, date, category);
    }
    private void delete(int id){
        Expenses.deleteExpense(id);
        this.load();
    }

}
