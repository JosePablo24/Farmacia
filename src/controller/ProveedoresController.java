/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conections.Conexion;
import far_system.LoginController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Person_system;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

/**
 * FXML Controller class
 *
 * @author Jose Pablo Sandoval
 */
public class ProveedoresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Conexion cc = new Conexion();
    private Connection cn= cc.conexion();    
    Person_system person;
        
    @FXML    private Button regresar;
    
    private ObservableList<ObservableList> data;
    @FXML private TableView tablaProveedores;
    
    @FXML private TextField nombreNuevoProv;

    @FXML private TextField telefonoNuevoProv;

    @FXML private TextField rfcNuevoProv;

    @FXML private TextField verNombreProv;

    @FXML private TextField verTelefonoProv;

    @FXML private TextField verRFCProv;
    
    @FXML private TextField verIdProv;
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        visualizateData();
    }
    
    @FXML
    void regresar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/Vista_principal.fxml"));
        Object carga = loader.load();
        Parent root = (Parent) carga;
        Scene scene = new Scene(root);            
        Vista_principalController controller = loader.<Vista_principalController>getController();
        controller.informacion(person);
        stage.setScene(scene);
        stage.show();                                                                   
        Stage stage1 = (Stage) regresar.getScene().getWindow();
        stage1.close();
    }
    
    
    @FXML
    void OnMouseClickedEditar(MouseEvent event) {

    }

    @FXML
    void OnMouseClickedEliminar(MouseEvent event) {

    }

    @FXML
    void OnMouseClickedNuevoProveedor(MouseEvent event) {
        if(nombreNuevoProv.getText().equals("") || telefonoNuevoProv.getText().equals("") || rfcNuevoProv.getText().equals("")){
            Alert dialogAlert2 = new Alert(Alert.AlertType.WARNING);
            dialogAlert2.setTitle("Advertencia");
            dialogAlert2.setContentText("Hay Campos Vacios");
            dialogAlert2.initStyle(StageStyle.UTILITY);
            dialogAlert2.showAndWait();
        }else{
            String nombre = nombreNuevoProv.getText();
            String telefono = telefonoNuevoProv.getText();
            String rfc = rfcNuevoProv.getText();
            String query = " INSERT INTO proveedores (Nombre, Rfc, Telefono)" +" Values (?, ?, ?)";
            try {
                PreparedStatement preparedStmt = cn.prepareStatement(query);
                preparedStmt.setString (1, nombre);
                preparedStmt.setString (2, rfc);
                preparedStmt.setString(3, telefono);
                preparedStmt.execute();
                cn.close();
                
                Alert dialogAlert2 = new Alert(Alert.AlertType.CONFIRMATION);
                dialogAlert2.setTitle("Exito");
                dialogAlert2.setContentText("Se guardo con exito");
                dialogAlert2.initStyle(StageStyle.UTILITY);
                dialogAlert2.showAndWait();
            } catch (SQLException e) {
                System.err.println("\nError!... No se pudo realizar la sentencia");
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);            
            }
        }
    }

        
    public void informacion(Person_system person){                
        this.person = person;
    }
    
    
    public void visualizateData(){
        String sql = "SELECT * FROM proveedores";
        data = FXCollections.observableArrayList();
        
         try {            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                       
             /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                tablaProveedores.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }
            
            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tablaProveedores.setItems(data);
        } catch (SQLException e) {
            System.err.println("\nMe llevo la ");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);            
        }
    }
    
}