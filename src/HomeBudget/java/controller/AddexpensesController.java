/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeBudget.java.controller;

import HomeBudget.java.model.ChoiceBoxItem;
import HomeBudget.java.model.Expenses;
import HomeBudget.java.model.ExpensesCategory;
import HomeBudget.java.model.Session;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Wielq
 */
public class AddexpensesController extends BaseController implements Initializable {

    public static final String URL_FXML = "/HomeBudget/resources/view/addexpenses.fxml";
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private DatePicker dateField;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXButton saveButton;
    @FXML
    private ChoiceBox<ChoiceBoxItem> categoryField;

    private TextFormatter<Double> textFormatter;
    @FXML
    private TextField idFIeld;
    @FXML
    private TextField idField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateField.setValue(LocalDate.now());
        Iterator list = ExpensesCategory.getExpensesCategory().iterator();
        if (list != null) {
            while (list.hasNext()) {
                ExpensesCategory expenseCategory = (ExpensesCategory) list.next();
                ChoiceBoxItem item = new ChoiceBoxItem(expenseCategory.getId(), expenseCategory.getName());
                categoryField.getItems().add(item);
            }
            categoryField.getSelectionModel().selectFirst();
        }

        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c;
            } else {
                return null;
            }
        };

        StringConverter<Double> converter = new StringConverter<Double>() {

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.00;
                } else {
                    return Double.valueOf(s);
                }
            }

            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };

        textFormatter = new TextFormatter<>(converter, 0.00, filter);
        priceField.setTextFormatter(textFormatter);

    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveExpense(ActionEvent event) {
        try {
            double price = textFormatter.getValue();
            String name = nameField.getText();
            int category = categoryField.getSelectionModel().getSelectedItem().value;
            java.sql.Date date = java.sql.Date.valueOf(dateField.getValue());
            int id = Integer.parseInt(idField.getText());
            if (id == 0) {
                //Expenses.insertExpense(Integer.parseInt(Session.getCurrentSession().get("user")), name, price, category, date);
            }else{
               // Expenses.editExpense(id,Integer.parseInt(Session.getCurrentSession().get("user")), name, price, category, date);
            }

        } catch (Exception e) {

        } finally {
            this.closeWindow(event);
        }
    }

    public void setForm(int id, String name, double price, java.sql.Date date, int category) {
        textFormatter.setValue(price);
        nameField.setText(name);
        dateField.setValue(date.toLocalDate());
        idField.setText(String.valueOf(id));
        int position = 0;
        for (ChoiceBoxItem item : categoryField.getItems()) {
            if (item.value == category) {
                break;
            }
            position++;
        }       
        categoryField.getSelectionModel().select(position);
    }

}
