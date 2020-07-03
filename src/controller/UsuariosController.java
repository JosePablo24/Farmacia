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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import conections.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

import java.util.ArrayList;


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
    private TableColumn<?,?> value0;
    
    @FXML
    private TableColumn<?,?>  value1;

    @FXML
    private TableColumn<?,?> value2;

    @FXML
    private TableColumn<?,?>  value3;

    @FXML
    private TableColumn<?,?>  value4;

    @FXML
    private TableColumn<?,?>  value5;

    @FXML
    private TableColumn<?,?>  value6;

    @FXML
    private TableColumn<?,?>  value7;
    

    @FXML
    private TextField search;
    
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
	int year;
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
                            apMat = rs.getString(4);
                            year = rs.getInt(5);
                            sex = rs.getString(6);
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
    
    @SuppressWarnings("unchecked")
	public void mostrarUsuarios() {
    	listaUsuarios.clear();
    	try {
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
                year = rs.getInt(6);
                sex = rs.getString(7);
                rl = rs.getString(8);
                listaUsuarios.addAll(new Person_system(id,username,name,apPat,apMat,year,sex,rl));
            }
            tableUsers.setColumnResizePolicy(tableUsers.CONSTRAINED_RESIZE_POLICY);

            TableColumn<Person_system, Number> value0 = new TableColumn<>("Id");
            value0.setCellValueFactory(c -> c.getValue().getId());

            TableColumn<Person_system, String> value1 = new TableColumn<>("Usuario");
            value1.setCellValueFactory(c -> c.getValue().getUsuario());

            TableColumn<Person_system, String> value2 = new TableColumn<>("Nombre");
            value2.setCellValueFactory(c -> c.getValue().getNameProperty());

            TableColumn<Person_system, String> value3 = new TableColumn<>("Apellido Paterno");
            value3.setCellValueFactory(c -> c.getValue().getApellidoPaterno());
            
            TableColumn<Person_system, String> value4 = new TableColumn<>("Apellido Materno");
            value4.setCellValueFactory(c -> c.getValue().getApellidoMaterno());
            
            TableColumn<Person_system, Number> value5 = new TableColumn<>("Edad");
            value5.setCellValueFactory(c -> c.getValue().getEdad());
            
            TableColumn<Person_system, String> value6 = new TableColumn<>("Sexo");
            value6.setCellValueFactory(c -> c.getValue().getSexo());
            
            TableColumn<Person_system, String> value7 = new TableColumn<>("Rol");
            value7.setCellValueFactory(c -> c.getValue().getRole());
           
            
            tableUsers.getColumns().clear();
            tableUsers.getColumns().add(value0);
            tableUsers.getColumns().add(value1);
            tableUsers.getColumns().add(value2);
            tableUsers.getColumns().add(value3);
            tableUsers.getColumns().add(value4);
            tableUsers.getColumns().add(value5);
            tableUsers.getColumns().add(value6);
            tableUsers.getColumns().add(value7);
            
            
	        FilteredList<Person_system> filteredData = new FilteredList<>(listaUsuarios, p -> true);

	        SortedList<Person_system> sortedData = new SortedList<>(filteredData);
	        sortedData.comparatorProperty().bind(tableUsers.comparatorProperty());

	        tableUsers.setItems(sortedData);
	        
	        search.textProperty().addListener((prop, old, text) -> {
	            System.out.println("Hekko");
	            filteredData.setPredicate(person -> {
	                if (text == null || text.isEmpty()) {
	                    return true;
	                }

	                System.out.println("Hekko");
	                String name = person.getName().toLowerCase();
	                return name.contains(text.toLowerCase());
	            });
	        });
            
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