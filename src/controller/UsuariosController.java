/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import conections.Conexion;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Person_system;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jose Pablo Sandoval
 */
public class UsuariosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML    
    private Button regresar;
    
    @FXML    
    private Button guardar;
    
    Person_system person;
    
    @FXML
    private TextField nombre;

    @FXML
    private TextField edad;

    @FXML
    private TextField apellidoPaterno;

    @FXML
    private TextField apellidoMaterno;

    @FXML
    private TextField sexo;
    
    @FXML
    private TextField usuario;

    @FXML
    private PasswordField password;
    
    @FXML
    private TextField rol;

    @FXML
    private TableView<Person_system> tableUsers;
    
    @FXML
    private TableColumn value0;

    @FXML
    private TableColumn value1;

    @FXML
    private TableColumn value2;

    @FXML
    private TableColumn value3;

    @FXML
    private TableColumn value4;

    @FXML
    private TableColumn value5;

    @FXML
    private TableColumn value6;

    @FXML
    private TableColumn value7;
    
    
    
    private ObservableList listaUsuarios = FXCollections.observableArrayList();
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    	mostrarUsuarios();
    }
    
    private Conexion cc = new Conexion();
    private Connection cn= cc.conexion();   
    
    @FXML
    void regresar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/Vista_principal.fxml"));
        Object carga = loader.load();
        Parent root = (Parent) carga;
        Scene scene = new Scene(root);            
        Vista_principalController controller = loader.<Vista_principalController>getController();
        System.out.println(controller);
        controller.informacion(person);
        stage.setScene(scene);
        stage.show();                                                                   
        Stage stage1 = (Stage) regresar.getScene().getWindow();
        stage1.close();
    }
    
	boolean band = false;
	int id;
	String name;
	String apPat;
	String apMat;
	String year;
	String sex;
	String username;
	String pass;
	String rl; 
	String Usuarios_id;
	
    @FXML
    void guardar(ActionEvent event)  throws IOException {    	                                                                              
    	if(!nombre.getText().equals("") && !apellidoPaterno.getText().equals("") && !apellidoMaterno.getText().equals("")&&!edad.getText().equals("")&& !sexo.getText().equals("")&& !usuario.getText().equals("")&&!password.getText().equals("") && !rol.getText().equals("")) {		
    		try {
    			if(Integer.parseInt(edad.getText())<0) {
        			System.err.println("No se puede");
            	}else {
            		String sql = "INSERT INTO Usuarios (Nombre,Apellido_Paterno,Apellido_materno,Edad,Sexo) VALUES ('"+nombre.getText()+"','"+apellidoPaterno.getText()+"','"+apellidoMaterno.getText()+"','"+edad.getText()+"','"+sexo.getText()+"')";   
                    try {            
                        Statement st = cn.createStatement();
            			st.executeUpdate(sql);
            			String query = "SELECT * FROM Usuarios WHERE Usuarios.Nombre = '"+ nombre.getText() +"'";
                        st = cn.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        while(rs.next()){
                            id = rs.getInt(1);
                            name = rs.getString(2);
                            apPat = rs.getString(3);
                            apMat = rs.getString(3);
                            year = rs.getString(4);
                            sex = rs.getString(5);
                            band = true;
                        }
                        if(band=true) {
                        	String sql2 = "INSERT INTO Login (User,Pasword,Rol,Usuarios_id) VALUES ('"+usuario.getText()+"','"+password.getText()+"','"+rol.getText()+"','"+id+"')";
                			st.executeUpdate(sql2);
                			String query2 = "SELECT * FROM Login";
                            st = cn.createStatement();
                            rs = st.executeQuery(query2);
                            clearInformation();
                			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                			alerta.setTitle("Advertencia");
                			alerta.setContentText("Usuario Agregado");
                			alerta.initStyle(StageStyle.UTILITY);
                			alerta.setHeaderText(null);
                			alerta.showAndWait();
                			mostrarUsuarios();
                        	cn.close();
                        }
                    } catch (SQLException e) {
                        Alert dialogAlert2 = new Alert(Alert.AlertType.WARNING);
                        dialogAlert2.setTitle("Advertencia");
                        dialogAlert2.setContentText("Error al guardar");
                        dialogAlert2.initStyle(StageStyle.UTILITY);
                        dialogAlert2.showAndWait();
                        Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, e);     
                        
                    }
            	}
			} catch (Exception e) {
                Alert dialogAlert2 = new Alert(Alert.AlertType.WARNING);
                dialogAlert2.setTitle("Advertencia");
                dialogAlert2.setContentText("La edad no puede incluir caracteres");
                dialogAlert2.initStyle(StageStyle.UTILITY);
                dialogAlert2.showAndWait();
			}
    	}else {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Advertencia");
			alerta.setContentText("Hay campos vacios vuelva a verificar");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
    	}

    
    }
    
    public void mostrarUsuarios() {
    	try {
        	listaUsuarios.clear();	
        	String sql = "SELECT Login.id, Login.User, Nombre, Apellido_paterno, Apellido_materno, Edad, Sexo, Rol FROM Usuarios INNER JOIN Login ON Usuarios.id = Login.Usuarios_id";
        	Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println(rs);
            while(rs.next()) {
            	id = rs.getInt(1);
            	username = rs.getString(2);
                name = rs.getString(3);
                apPat = rs.getString(4);
                apMat = rs.getString(5);
                year = rs.getString(6);
                sex = rs.getString(7);
                rl = rs.getString(8);
                System.out.println(year+sex);
                Person_system person = new Person_system(id,username,name,apPat,apMat,year,sex,rl);
                listaUsuarios.addAll(person);
            }
            
            value0.setCellValueFactory(
                    new PropertyValueFactory<Person_system, String>("id"));
            value1.setCellValueFactory(
                    new PropertyValueFactory<Person_system, String>("user"));
            value2.setCellValueFactory(
                    new PropertyValueFactory<Person_system, String>("Nombre"));
            value3.setCellValueFactory(
                    new PropertyValueFactory<Person_system, String>("apellidoPaterno"));
            value4.setCellValueFactory(
                    new PropertyValueFactory<Person_system, String>("apellidoMaterno"));
            value5.setCellValueFactory(
                    new PropertyValueFactory<Person_system, String>("edad"));
            value6.setCellValueFactory(
                    new PropertyValueFactory<Person_system, String>("sexo"));
            value7.setCellValueFactory(
                    new PropertyValueFactory<Person_system, String>("rol"));
			tableUsers.setItems(listaUsuarios);
			tableUsers.getItems();
            
		} catch (Exception e) {
			System.err.println("\nMe llevo la ");
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, e); 
		}
    }
    
    public void clearInformation() {
    	nombre.clear();
    	edad.clear();
    	apellidoPaterno.clear();
    	apellidoMaterno.clear();
    	sexo.clear();
    	usuario.clear();
    	password.clear();
    	rol.clear();
    }
    
    public void informacion(Person_system person){                
        this.person = person;
    }
    
    
}