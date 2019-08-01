package HomeBudget.java.model.tableview;

import HomeBudget.java.controller.AddexpensesController;
import HomeBudget.java.model.ChoiceBoxItem;
import HomeBudget.java.model.Expenses;
import HomeBudget.java.model.ExpensesCategory;
import HomeBudget.java.model.Navigation;
import HomeBudget.java.model.Session;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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

    private final int userid = Integer.parseInt(Session.getCurrentSession().get("user"));

    /**
     * Set column and filters
     */
    public ExpensesTableView() {
        super();
        ArrayList<ChoiceBoxItem> categories = new ArrayList();
        Iterator list = ExpensesCategory.getExpensesCategory().iterator();
        if (list != null) {
            while (list.hasNext()) {
                ExpensesCategory expenseCategory = (ExpensesCategory) list.next();
                categories.add(new ChoiceBoxItem(expenseCategory.getId(), expenseCategory.getName()));
            }
        }            
        Column name = new Column("name")
                .setCaption("Nazwa")
                .addInputFilter(this, "Nazwa wydatku...");
        Column category = new Column("category.id")
                .setCaption("Kategoria")
                .addSelectFilter(this, "Wszystkie",categories);
        Column price = new Column("price")
                .setCaption("Kwota")
                .addLessDoubleFilter(this, "Od...")
                .addMoreDoubleFilter(this, "Do...");
        Column date = new Column("date")
                .setCaption("Data")
                .addLessDateFilter(this, "Od...")
                .addMoreDateFilter(this, "Do...");
        Column action = new Column("Akcje")
                .setCaption("Akcje");
        super.setColumns(name, category, price, date, action);
        this.pagination.setItemsPerPage(this.limit);
    }

    @Override
    /**
     * Add data
     */
    public void load() {
        super.clean();

        int numbers = (int) Expenses.getCount(userid, filters);
        this.pagination.setItemsMax(numbers);
        pagination.generatePagination(this);

        DecimalFormat df = new DecimalFormat("0.00"); 
        
        Iterator list = Expenses.getExpenses(userid, limit, filters, this.pagination.getPage()).iterator();
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

                Cell Cprice = new Cell();
                Cprice.SimpleLabelCell(df.format(Eprice));

                Cell Cdate = new Cell();
                Cdate.SimpleLabelCell(Edate.toString());

                Cell Caction = new Cell();

                Button edit = GlyphsDude.createIconButton(FontAwesomeIcon.EDIT, "", "15px", "15px", ContentDisplay.GRAPHIC_ONLY);
                edit.getStyleClass().add("yellow");
                edit.setOnAction(e -> {
                    this.edit(expense.getId(), Ename, Ecategory.getId(), Eprice, Edate);
                });
                Caction.addRowButton(edit);

                Button delete = GlyphsDude.createIconButton(FontAwesomeIcon.REMOVE, "", "15px", "15px", ContentDisplay.GRAPHIC_ONLY);
                delete.getStyleClass().add("negative");
                delete.setOnAction(e -> {
                    this.delete(expense.getId());
                });
                Caction.addRowButton(delete);

                super.addRow(Cname, Ccategory, Cprice, Cdate, Caction);
            }          
            Double sum = Expenses.getSum(userid, filters);
            super.addRow(new Cell().addClass("foot-cell").SimpleLabelCell("Podsumowanie:"),new Cell().addClass("foot-cell"),new Cell().addClass("foot-cell").SimpleLabelCell(df.format(sum)),new Cell().addClass("foot-cell"),new Cell().addClass("foot-cell"));
            
        }
    }

    private void edit(int id, String name, int category, double price, java.sql.Date date) {
        AddexpensesController controller = (AddexpensesController) Navigation.getNavigation().openModal(AddexpensesController.URL_FXML, this);
        controller.setForm(id, name, price, date, category);
    }

    private void delete(int id) {
        Expenses.deleteExpense(id);
        this.load();
    }

}
