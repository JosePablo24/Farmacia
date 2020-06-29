/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Person_system;
/**
 * FXML Controller class
 *
 * @author Jose Pablo Sandoval
 */
public class Vista_principalController implements Initializable {
   

    @FXML    private Label DateTime;
    @FXML    private Label Name;
    @FXML    private Label Rol;
    @FXML    private Button Productos;
    @FXML    private Button Usuarios;
    @FXML    private Button Provedores;
    @FXML    private Button Ventas;
    @FXML    private Button Corte;
    @FXML    private Button Cambio;
    @FXML    private TextField Cambio_caja;
    Person_system person;
    String nombre;
    String rol;
    String user;   
    float cambio;
    

    @FXML
    void Cambio(ActionEvent event) {
        person.setCambio(Float.parseFloat(Cambio_caja.getText()));
        Cambio.setDisable(true);
        Alert dialogAlert2 = new Alert(Alert.AlertType.INFORMATION);
        dialogAlert2.setTitle("Informe");
        dialogAlert2.setContentText("Se agrego dinero a la caja");
        dialogAlert2.initStyle(StageStyle.UTILITY);
        dialogAlert2.showAndWait();
    }

    @FXML
    void Corte_caja(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/Usuarios.fxml"));
        Object carga = loader.load();
        Parent root = (Parent) carga;
        Scene scene = new Scene(root);            
        UsuariosController controller = loader.<UsuariosController>getController();
        controller.informacion(person);
        stage.setScene(scene);
        stage.show();                                                                   
        Stage stage1 = (Stage) Corte.getScene().getWindow();
        stage1.close();

    }

    @FXML
    void Productos(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/Productos.fxml"));
        Object carga = loader.load();
        Parent root = (Parent) carga;
        Scene scene = new Scene(root);            
        ProductosController controller = loader.<ProductosController>getController();
        controller.informacion(person);
        stage.setScene(scene);
        stage.show();                                                                   
        Stage stage1 = (Stage) Corte.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void Proveedores(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/Proveedores.fxml"));
        Object carga = loader.load();
        Parent root = (Parent) carga;
        Scene scene = new Scene(root);            
        ProveedoresController controller = loader.<ProveedoresController>getController();
        controller.informacion(person);
        stage.setScene(scene);
        stage.show();                                                                   
        Stage stage1 = (Stage) Corte.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void Usuarios(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/Usuarios.fxml"));
        Object carga = loader.load();
        Parent root = (Parent) carga;
        Scene scene = new Scene(root);            
        UsuariosController controller = loader.<UsuariosController>getController();
        controller.informacion(person);
        stage.setScene(scene);
        stage.show();                                                                   
        Stage stage1 = (Stage) Corte.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void Ventas(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/Ventas.fxml"));
        Object carga = loader.load();
        Parent root = (Parent) carga;
        Scene scene = new Scene(root);            
        VentasController controller = loader.<VentasController>getController();
        controller.informacion(person);
        stage.setScene(scene);
        stage.show();                                                                   
        Stage stage1 = (Stage) Corte.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void out(ActionEvent event) {
        Alert dialogAlert2 = new Alert(Alert.AlertType.WARNING);
        dialogAlert2.setTitle("Advertencia");
        dialogAlert2.setContentText("Esta saliendo de la aplciación");
        dialogAlert2.initStyle(StageStyle.UTILITY);
        dialogAlert2.showAndWait();
        System.exit(0);
    }

    public void informacion(Person_system person){                
        this.person = person;
        this.nombre = person.getNombre();
        this.rol = person.getRol();
        this.cambio = person.getCambio();
        Name.setText(person.getNombre());
        Rol.setText(person.getRol());         
        if(rol.equals("Admin")){            
        }else{
            Usuarios.setVisible(false);
        }
        if(cambio <= 50){
            Alert dialogAlert2 = new Alert(Alert.AlertType.WARNING);
            dialogAlert2.setTitle("Advertencia");
            dialogAlert2.setContentText("No hay cambio en la caja");
            dialogAlert2.initStyle(StageStyle.UTILITY);
            dialogAlert2.showAndWait();
        }else {
            Cambio.setDisable(true);
            Cambio_caja.setText(String.valueOf(cambio));
        }
    }
    
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        DateTime.setText(Fecha());
    }
     
    public static String Fecha(){
         Date fecha = new Date();
         SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY");
         return formato.format(fecha);                  
     }
}

