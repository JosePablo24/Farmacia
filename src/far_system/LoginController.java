/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package far_system;

import java.net.URL;
import conections.Conexion;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conections.Conectar;
import conections.Conection;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;

/**
 *
 * @author Jose Pablo Sandoval
 */
public class LoginController implements Initializable {

    @FXML    private TextField user;
    @FXML    private PasswordField password;
    @FXML    private Button sing_in;    
    @FXML    private Button out;  
    
    private Conexion cc = new Conexion();
    private Connection cn= cc.conexion();    
    
    @FXML
    public void initialize(URL location, ResourceBundle resources) {                	                
    }
    
    @FXML
    void out(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void sign_in(ActionEvent event) {
        if(password.getText().equals("") || user.getText().equals("")){
            Alert dialogAlert2 = new Alert(Alert.AlertType.WARNING);
            dialogAlert2.setTitle("Advertencia");
            dialogAlert2.setContentText("Hay Campos Vacios");
            dialogAlert2.initStyle(StageStyle.UTILITY);
            dialogAlert2.showAndWait();
        }else{
            logear(user.getText(), password.getText());
        }
    }
    
    @FXML
    void sign_in(KeyEvent event) throws IOException {
        if(event.getCode().toString().equals("ENTER")){
            if(password.getText().equals("") || user.getText().equals("")){
                Alert dialogAlert2 = new Alert(Alert.AlertType.WARNING);
                dialogAlert2.setTitle("Advertencia");
                dialogAlert2.setContentText("Hay Campos Vacios");
                dialogAlert2.initStyle(StageStyle.UTILITY);
                dialogAlert2.showAndWait();
            }else{
                logear(user.getText(), password.getText());
            }
        }

    }
    
    int id;
    String User;
    String Nombre;
    String ApellidoP;
    String ApellidoM;
    String userDb;
    public void logear(String user, String password){
        System.out.println(password + user);
        String passDb = null;
        //SELECT * FROM farmacia.login where User = 'joxesandoval'
        String sql = "SELECT * FROM farmacia.login WHERE User = '"+ user +"'";
        try {
            System.out.println("\nentre");
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                id=rs.getInt(1);
                User = rs.getString(2);
            }            
        } catch (SQLException e) {
            System.err.println("\nMe llevo la ");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            
        }
    }
    
}
