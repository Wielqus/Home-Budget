package HomeBudget.java.controller;

import HomeBudget.java.model.Expenses;
import HomeBudget.java.model.Incomes;
import HomeBudget.java.model.Session;
import com.jfoenix.controls.JFXProgressBar;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.temporal.TemporalQueries;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.hibernate.metamodel.relational.Size;

/**
 * FXML Controller class
 *
 * @author Wielq
 */
public class HomeController extends BaseController implements Initializable {
    
    public static final String URL_FXML = "/HomeBudget/resources/view/home.fxml";
    @FXML
    private FontAwesomeIconView homeIcon;
    @FXML
    private Label actualSum;
    @FXML
    private JFXProgressBar progress;
    
    private final int userid = Integer.parseInt(Session.getCurrentSession().get("user"));
    @FXML
    private GridPane Grid;
    @FXML
    private VBox raportsBox;
    
    /**
     * Initializes the controller class.
     * Generate chart
     * Set sum
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Double sum = (Double)Incomes.getSum(this.userid, new ArrayList())-Expenses.getSum(userid, new ArrayList());
        DecimalFormat df = new DecimalFormat("0.00"); 
        actualSum.setText(df.format(sum));
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Data");
        yAxis.setLabel("Bilans");
        //creating the chart
        final LineChart<String, Number> lineChart
                = new LineChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle("Bilans z ostatnich 7 dni");
        XYChart.Series series = new XYChart.Series();
        series.setName("Bilans");

        long time = System.currentTimeMillis();
        Date date = new Date(time);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -7);

        Double result = (Double) Expenses.getResultToDay(this.userid, new ArrayList(), new Date(c.getTimeInMillis())).iterator().next();
        series.getData().add(new XYChart.Data(new Date(c.getTimeInMillis()).toString(), result));

        for (int i = 1; i <= 7; i++) {
            c.add(Calendar.DATE, 1);
            result += (Double) Expenses.getResultFromDay(this.userid, new ArrayList(), new Date(c.getTimeInMillis())).iterator().next();
            series.getData().add(new XYChart.Data(new Date(c.getTimeInMillis()).toString(), result));
        }

        lineChart.getData().add(series);
        lineChart.setMaxWidth(Double.MAX_VALUE);
        lineChart.setMaxHeight(Double.MAX_VALUE);
        raportsBox.getChildren().add(lineChart);
       
    }    
    
}
