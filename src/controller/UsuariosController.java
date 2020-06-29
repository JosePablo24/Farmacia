/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.Person_system;

/**
 * FXML Controller class
 *
 * @author Jose Pablo Sandoval
 */
public class UsuariosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Person_system person;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void informacion(Person_system person){                
        this.person = person;
    }
    
}
