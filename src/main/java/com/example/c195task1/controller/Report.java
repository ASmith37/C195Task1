package com.example.c195task1.controller;

import com.example.c195task1.helper.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Report implements Initializable {
    public Label lblReport;
    public Button btnClose;
    public TableView tblReport;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public class Report1Class {
        String type;
        int year;
        String month;
        int count;

        public Report1Class(String type, int year, String month, int count) {
            this.type = type;
            this.year = year;
            this.month = month;
            this.count = count;
        }
        public String getType() {
            return type;
        }
        public int getYear() {
            return year;
        }
        public String getMonth() {
            return month;
        }
        public int getCount() {
            return count;
        }

    }
    public void setModeReport1() throws SQLException {
        lblReport.setText("Total customer appointments by type and month");
        String sql = "SELECT `Type`, YEAR(Start) as Year, monthname(str_to_date(MONTH(Start), '%m')) as Month, COUNT(*) as Count\n" +
                "FROM appointments\n" +
                "GROUP BY `Type`, YEAR(Start), MONTH(Start)";
        TableColumn t1 = new TableColumn("Type");
        TableColumn t2 = new TableColumn("Year");
        TableColumn t3 = new TableColumn("Month");
        TableColumn t4 = new TableColumn("Count");
        tblReport.getColumns().addAll(t1, t2, t3, t4);
        t1.setCellValueFactory(new PropertyValueFactory<>("type"));
        t2.setCellValueFactory(new PropertyValueFactory<>("year"));
        t3.setCellValueFactory(new PropertyValueFactory<>("month"));
        t4.setCellValueFactory(new PropertyValueFactory<>("count"));
        t1.setPrefWidth(100.0);
        t2.setPrefWidth(100.0);
        t3.setPrefWidth(100.0);
        t4.setPrefWidth(100.0);
        ObservableList<Report1Class> data = FXCollections.observableArrayList();
        DBConnection.openConnection();
        Statement s = DBConnection.connection.createStatement();
        ResultSet r = s.executeQuery(sql);
        while (r.next()) {
            data.add(new Report1Class(
                    r.getString(1),
                    r.getInt(2),
                    r.getString(3),
                    r.getInt(4)
            ));
        }
        DBConnection.closeConnection();
        tblReport.setItems(data);
    }
    public class Report2Class {
        String employee;
        int appointmentId;
        String title;
        String type;
        String description;
        LocalDateTime start;
        LocalDateTime end;
        int customerId;

        public Report2Class(String employee, int appointmentId, String title, String type, String description, LocalDateTime start, LocalDateTime end, int customerId) {
            this.employee = employee;
            this.appointmentId = appointmentId;
            this.title = title;
            this.type = type;
            this.description = description;
            this.start = start;
            this.end = end;
            this.customerId = customerId;
        }

        public String getEmployee() {
            return employee;
        }

        public int getAppointmentId() {
            return appointmentId;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }

        public LocalDateTime getStart() {
            return start;
        }

        public LocalDateTime getEnd() {
            return end;
        }

        public int getCustomerId() {
            return customerId;
        }
    }
    public void setModeReport2() throws SQLException {
        lblReport.setText("Schedule");
        String sql = "SELECT Contact_Name as Employee, Appointment_ID, `Title`, `Type`, `Description`, Start, End, Customer_ID\n" +
                "FROM appointments\n" +
                "LEFT JOIN contacts\n" +
                "USING (Contact_ID)\n" +
                "ORDER BY Start ASC, Contact_Name ASC";
        TableColumn t1 = new TableColumn("Employee");
        TableColumn t2 = new TableColumn("Appointment ID");
        TableColumn t3 = new TableColumn("Title");
        TableColumn t4 = new TableColumn("Type");
        TableColumn t5 = new TableColumn("Description");
        TableColumn t6 = new TableColumn("Start");
        TableColumn t7 = new TableColumn("End");
        TableColumn t8 = new TableColumn("Customer ID");
        tblReport.getColumns().addAll(t1, t2, t3, t4, t5, t6, t7, t8);
        t1.setCellValueFactory(new PropertyValueFactory<>("employee"));
        t2.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        t3.setCellValueFactory(new PropertyValueFactory<>("title"));
        t4.setCellValueFactory(new PropertyValueFactory<>("type"));
        t5.setCellValueFactory(new PropertyValueFactory<>("description"));
        t6.setCellValueFactory(new PropertyValueFactory<>("start"));
        t7.setCellValueFactory(new PropertyValueFactory<>("end"));
        t8.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        t1.setPrefWidth(100.0);
        t2.setPrefWidth(100.0);
        t3.setPrefWidth(100.0);
        t4.setPrefWidth(100.0);
        t5.setPrefWidth(100.0);
        t6.setPrefWidth(150.0);
        t7.setPrefWidth(150.0);
        t8.setPrefWidth(100.0);
        ObservableList<Report2Class> data = FXCollections.observableArrayList();
        DBConnection.openConnection();
        Statement s = DBConnection.connection.createStatement();
        ResultSet r = s.executeQuery(sql);
        while (r.next()) {
            data.add(new Report2Class(
                    r.getString(1),
                    r.getInt(2),
                    r.getString(3),
                    r.getString(4),
                    r.getString(5),
                    r.getTimestamp(6).toLocalDateTime(),
                    r.getTimestamp(7).toLocalDateTime(),
                    r.getInt(8)
            ));
        }
        DBConnection.closeConnection();
        tblReport.setItems(data);
    }
    public class Report3Class {
        int year;
        int week;
        String contactName;
        double billableHours;

        public Report3Class(int year, int week, String contactName, double billableHours) {
            this.year = year;
            this.week = week;
            this.contactName = contactName;
            this.billableHours = billableHours;
        }

        public int getYear() {
            return year;
        }

        public int getWeek() {
            return week;
        }

        public String getContactName() {
            return contactName;
        }

        public double getBillableHours() {
            return billableHours;
        }
    }
    public void setModeReport3() throws SQLException {
        lblReport.setText("Billable hours by employee and week");
        String sql = "SELECT YEAR(Start) as Year, WEEK(Start) as Week, Contact_Name, SUM(time_to_sec(timediff(End, Start)))/60/60 as Billable_Hours\n" +
                "FROM appointments\n" +
                "LEFT JOIN contacts\n" +
                "USING (Contact_ID)\n" +
                "GROUP BY Contact_ID, YEAR(Start), WEEK(Start)\n" +
                "ORDER BY Year ASC, Week ASC, Contact_Name ASC";
        TableColumn t1 = new TableColumn("Year");
        TableColumn t2 = new TableColumn("Week");
        TableColumn t3 = new TableColumn("Employee");
        TableColumn t4 = new TableColumn("Billable Hours");
        tblReport.getColumns().addAll(t1, t2, t3, t4);
        t1.setCellValueFactory(new PropertyValueFactory<>("year"));
        t2.setCellValueFactory(new PropertyValueFactory<>("week"));
        t3.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        t4.setCellValueFactory(new PropertyValueFactory<>("billableHours"));
        t1.setPrefWidth(100.0);
        t2.setPrefWidth(100.0);
        t3.setPrefWidth(100.0);
        t4.setPrefWidth(100.0);
        ObservableList<Report3Class> data = FXCollections.observableArrayList();
        DBConnection.openConnection();
        Statement s = DBConnection.connection.createStatement();
        ResultSet r = s.executeQuery(sql);
        while (r.next()) {
            data.add(new Report3Class(
                    r.getInt(1),
                    r.getInt(2),
                    r.getString(3),
                    r.getDouble(4)
            ));
        }
        DBConnection.closeConnection();
        tblReport.setItems(data);
    }
    public void onBtnClose(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/MainScreen.fxml")); // javafx.fxml.LoadException
        Parent root = loader.load();
        stage.setTitle("Main Screen");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
