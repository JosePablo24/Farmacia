/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Person_system;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import conections.Conexion;

import java.sql.Connection;

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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView.TableViewSelectionModel;


/**
 * FXML Controller class
 *
 * @author Jose Pablo Sandoval
 */
public class ProductosController implements Initializable {

    /**
     * Initializes the controller class.
     */
	private Conexion cc = new Conexion();
    private Connection cn = cc.conexion();
    @FXML    
    private Button regresar;
    Person_system person;
    private ObservableList<ObservableList> data;
    @FXML
    private TableView verTabla;
    @FXML
    private Button botonNuevo;
    @FXML
    private TextField nuevoNombrePro;
    @FXML
    private TextField nuevoDescripcionPro;
    @FXML
    private TextField nuevoCantidadPro;
    @FXML
    private TextField editarNombrePro;
    @FXML
    private TextField editarDescripcionPro;
    @FXML
    private TextField editarCantidadPro;
    @FXML
    private Button botonEditar;
    @FXML
    private Button botonEliminar;
    @FXML
    private TextField editarIDProv;
    @FXML
    private TextField nuevoIVAPro;
    @FXML
    private TextField nuevoPrecioPro;
    @FXML
    private TextField nuevoIDProvPro;
    @FXML
    private TextField editarIVAProv;
    @FXML
    private TextField editarPrecioPro;
    @FXML
    private TextField editarIDProvePro;
    @FXML
    private Button botonBuscar;
    @FXML
    private TextField buscarIDPro;
    @FXML
    private Button botonMostrar;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	updateInfoTable();
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
    private void nuevo(ActionEvent event) {
    	
    }

    @FXML
    private void editar(ActionEvent event) {
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }

    @FXML
    private void buscar(ActionEvent event) {
    }

    @FXML
    private void OnMouseClcikedMostrarTodo(MouseEvent event) {
    }

    @FXML
    private void mostrar(ActionEvent event) {
    	updateInfoTable();
    }
    
    private void updateInfoTable(){
        String sql = "SELECT * FROM productos";
        data = FXCollections.observableArrayList();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    
                	public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                verTabla.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            
            
            
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
            
            //verTabla;
            //TableColumn<String, Person> column1 = new TableColumn<>("Nombre");

            //FINALLY ADDED TO TableView
            
            verTabla.setItems(data);
            //verTabla.refresh();
        } catch (Exception e) {

            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
    }
    
    public void informacion(Person_system person){                
        this.person = person;
    }
    
    
}


