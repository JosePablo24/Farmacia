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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Person_system;
/**
 * FXML Controller class
 *
 * @author Jose Pablo Sandoval
 */
public class ProveedoresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML    private Button regresar;
    Person_system person;
    
    @FXML
    private TextField nombreNuevoProv;

    @FXML
    private TextField telefonoNuevoProv;

    @FXML
    private TextField rfcNuevoProv;

    @FXML
    private TextField verNombreProv;

    @FXML
    private TextField verTelefonoProv;

    @FXML
    private TextField verRFCProv;
    
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
    void OnMouseClickedEditar(MouseEvent event) {

    }

    @FXML
    void OnMouseClickedEliminar(MouseEvent event) {

    }

    @FXML
    void OnMouseClickedNuevoProveedor(MouseEvent event) {
    
    }

        
    public void informacion(Person_system person){                
        this.person = person;
    }
    
    
}