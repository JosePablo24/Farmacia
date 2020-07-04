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
/**
 * FXML Controller class
 *
 * @author Jose Pablo Sandoval
 */
public class ProductosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML    
    private Button regresar;
    Person_system person;
    @FXML
    private TableView<?> verTabla;
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
    }
    
    public void informacion(Person_system person){                
        this.person = person;
    }
    
}
