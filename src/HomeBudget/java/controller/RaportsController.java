package HomeBudget.java.controller;

import HomeBudget.java.model.Expenses;
import HomeBudget.java.model.Expenses;
import HomeBudget.java.model.Incomes;
import HomeBudget.java.model.Session;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Wielq
 */
public class RaportsController extends BaseController implements Initializable {

    public static final String URL_FXML = "/HomeBudget/resources/view/raports.fxml";
    @FXML
    private GridPane Grid;

    private final int userid = Integer.parseInt(Session.getCurrentSession().get("user"));
    @FXML
    private HBox PieCharHBox;
    @FXML
    private VBox Pie1Box;
    @FXML
    private VBox Pie2Box;
    @FXML
    private VBox lineChartBox;

    /**
     * Initializes the controller class.
     * Generates charts
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> expensesCategoriesData = FXCollections.observableArrayList();

        Iterator expensesCategoriesPercent = Expenses.getPercentCategoriesPart(this.userid, new ArrayList()).iterator();
        if (!expensesCategoriesPercent.hasNext()) {
            expensesCategoriesData.add(new PieChart.Data("Brak danych", 100));
        }
        while (expensesCategoriesPercent.hasNext()) {
            Object[] data = (Object[]) expensesCategoriesPercent.next();
            expensesCategoriesData.add(new PieChart.Data((String) data[1], (Double) data[0]));

        }

        final PieChart ExpensesCategorieschart = new PieChart(expensesCategoriesData);
        ExpensesCategorieschart.setTitle("Na co wydajesz pieniądze");
        Pie1Box.getChildren().add(ExpensesCategorieschart);

        ObservableList<PieChart.Data> incomesCategoriesData = FXCollections.observableArrayList();

        Iterator incomesCategoriesPercent = Incomes.getPercentCategoriesPart(this.userid, new ArrayList()).iterator();
        if (!incomesCategoriesPercent.hasNext()) {
            incomesCategoriesData.add(new PieChart.Data("Brak danych", 100));
        }
        while (incomesCategoriesPercent.hasNext()) {
            Object[] data = (Object[]) incomesCategoriesPercent.next();
            incomesCategoriesData.add(new PieChart.Data((String) data[1], (Double) data[0]));

        }

        final PieChart IncomesCategorieschart = new PieChart(incomesCategoriesData);
        IncomesCategorieschart.setTitle("Na czym zarabiasz pieniądze");
        Pie2Box.getChildren().add(IncomesCategorieschart);

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Data");
        yAxis.setLabel("Bilans");
        //creating the chart
        final LineChart<String, Number> lineChart
                = new LineChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle("Bilans z ostatnich 14 dni");
        XYChart.Series series = new XYChart.Series();
        series.setName("Bilans");

        long time = System.currentTimeMillis();
        Date date = new Date(time);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -14);

        Double result = (Double) Expenses.getResultToDay(this.userid, new ArrayList(), new Date(c.getTimeInMillis())).iterator().next();
        series.getData().add(new XYChart.Data(new Date(c.getTimeInMillis()).toString(), result));

        for (int i = 1; i <= 14; i++) {
            c.add(Calendar.DATE, 1);
            result += (Double) Expenses.getResultFromDay(this.userid, new ArrayList(), new Date(c.getTimeInMillis())).iterator().next();
            series.getData().add(new XYChart.Data(new Date(c.getTimeInMillis()).toString(), result));
        }

        lineChart.getData().add(series);
        lineChart.setMaxWidth(Double.MAX_VALUE);
        lineChart.setMaxHeight(Double.MAX_VALUE);
        lineChartBox.getChildren().add(lineChart);

    }

}
