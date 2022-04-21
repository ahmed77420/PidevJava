/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import Services.ServicesResoffre;
import edu.entities.Resoffre;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hbaie
 */
public class ResoffreViewController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private Button create;
    @FXML
    private TextField tfnbrplace;
    @FXML
    private TableColumn<Resoffre, String> colclient;
    @FXML
    private TableColumn<Resoffre, String> coloffre;
    @FXML
    private TableColumn<Resoffre, Date> coldateres;
    @FXML
    private TableColumn<Resoffre, Integer> colnbrdeplace;
    @FXML
    private DatePicker tfdateres;
    @FXML
    private ComboBox<String> comboclient;
    @FXML
    private ComboBox<String> combooffre;
    @FXML
    private TableView<Resoffre> tableresoffre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicesResoffre resoffreserv = new ServicesResoffre();

        showResoffre();
        comboclient.setItems(resoffreserv.GetListClient()) ; 
        combooffre.setItems(resoffreserv.GetListOffre()) ; 

        tableresoffre.setOnMouseClicked( e -> {
            Resoffre resoff = new Resoffre();
            resoff = tableresoffre.getSelectionModel().getSelectedItem() ; 
            //System.out.println(g.getName()) ;
            tfid.setText(String.valueOf(resoff.getId()) ) ; 
            tfnbrplace.setText(String.valueOf(resoff.getNbr_place() ) ) ; 
            tfdateres.setValue(resoff.getDate_res().toLocalDate() ) ; 


           // System.out.println(off.toString()) ;


            });
        



    }    



    public void showResoffre()
    {
        ServicesResoffre resoffreserv = new ServicesResoffre();
        ObservableList list = resoffreserv.ViewResoffrefx(); 
        coldateres.setCellValueFactory( new PropertyValueFactory<Resoffre,Date>("date_res") );
        colnbrdeplace.setCellValueFactory( new PropertyValueFactory<Resoffre,Integer>("nbr_place") );
        colclient.setCellValueFactory( cellData -> new SimpleStringProperty(resoffreserv.GetClientName(cellData.getValue().getId_user() ) ) );
        coloffre.setCellValueFactory( cellData -> new SimpleStringProperty(resoffreserv.GetOffreName(cellData.getValue().getId_offre() ) ) );

        tableresoffre.setItems(list);
        

    }



    @FXML
    private void delete_resoffre(ActionEvent event) {
        Resoffre resoff = new Resoffre();
        resoff.setId(Integer.parseInt( tfid.getText())) ; 
        
        ServicesResoffre resoffreserv = new ServicesResoffre();
        resoffreserv.DeleteResoffre(resoff);
        showResoffre() ; 
    }

    @FXML
    private void update_resoffre(ActionEvent event) {
        ServicesResoffre resoffreserv = new ServicesResoffre();
        Resoffre resoff = new Resoffre();
        resoff.setId(Integer.parseInt(tfid.getText()));
        resoff.setNbr_place(Integer.parseInt(tfnbrplace.getText() ) );
        resoff.setDate_res(Date.valueOf(tfdateres.getValue() ));
        resoff.setId_offre(resoffreserv.GetOffreId( combooffre.getSelectionModel().getSelectedItem().toString()));
        resoff.setId_user(resoffreserv.GetClientId( comboclient.getSelectionModel().getSelectedItem().toString()));

        resoffreserv.UpdateResoffre(resoff);
        showResoffre() ; 
    }

    @FXML
    private void create_resoffre(ActionEvent event) {
        ServicesResoffre resoffreserv = new ServicesResoffre();
        Resoffre resoff = new Resoffre();
        resoff.setNbr_place(Integer.parseInt(tfnbrplace.getText() ) );
        resoff.setDate_res(Date.valueOf(tfdateres.getValue() ));
        resoff.setId_offre(resoffreserv.GetOffreId( combooffre.getSelectionModel().getSelectedItem().toString()));
        resoff.setId_user(resoffreserv.GetClientId( comboclient.getSelectionModel().getSelectedItem().toString()));

        resoffreserv.CreateResoffre(resoff);
        showResoffre() ; 


    }
    
}
