package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.tm.CustomerTm;
import lk.ijse.model.CustomerModel;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {

    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTele;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colTele;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    private CustomerModel cusModel = new CustomerModel();

    public void initialize() {
        setCellValueFactory();
        loadAllCustomer();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTele.setCellValueFactory(new PropertyValueFactory<>("tele"));
    }

    private void loadAllCustomer() {
        var model = new CustomerModel();

        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> dtoList = model.getAllCustomer();

            for (CustomerDto dto : dtoList) {
                obList.add(
                        new CustomerTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getTele()
                        )
                );
            }

            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tele = Integer.parseInt(txtTele.getText());

        var dto = new CustomerDto(id, name, address, tele);

        try {
            boolean isSaved = cusModel.saveCustomer(dto);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        initialize();
    }

    private void clearFields() {
        txtID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTele.setText("");
    }
}
