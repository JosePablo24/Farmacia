/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jose Pablo Sandoval
 */
public class Person_system {
    public String user;
    public float cambio;
    public String rol;
    public String Nombre;
    
    public final StringProperty name;
    public final StringProperty apellidoPaterno;
    public final StringProperty apellidoMaterno;
    public final IntegerProperty edad;
    public final StringProperty sexo;
    public final IntegerProperty id;
    public final StringProperty role;
    public final StringProperty usuario;
    
    public Person_system(){
    	this.usuario = new SimpleStringProperty();
    	this.name = new SimpleStringProperty();
    	this.apellidoMaterno = new SimpleStringProperty();
    	this.apellidoPaterno = new SimpleStringProperty();
    	this.edad = new SimpleIntegerProperty();
    	this.sexo = new SimpleStringProperty();
    	this.id = new SimpleIntegerProperty();
    	this.role = new SimpleStringProperty();
    	
		this.Nombre = "";
        this.rol = "";
        this.user = "";
        this.cambio = 0;
    }
    
    public Person_system(Integer id, String usuario, String name,String apellidoPaterno,String apellidoMaterno, Integer edad,String sexo, String role){
		this.id= new SimpleIntegerProperty(id);
    	this.name = new SimpleStringProperty(name);
        this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
        this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
        this.edad = new SimpleIntegerProperty(edad);
        this.sexo = new SimpleStringProperty(sexo);
        this.usuario = new SimpleStringProperty(usuario);
        this.role = new SimpleStringProperty(role);
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
        System.out.println(Nombre);
    }        

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
        System.out.println(user);
    }

    public float getCambio() {
        return cambio;
    }

    public void setCambio(float cambio) {
        this.cambio = cambio;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
        System.out.println(rol);
    }   
    
    
    public void setName(String name) {
    	this.name.set(name);;
    }
    
    public String getName() {
    	return name.get();
    }
    
    public StringProperty getNameProperty() {
    	return name;
    }
    
    public void setUsuario(String usuario) {
    	this.usuario.set(usuario);;
    }
    
    public StringProperty getUsuario() {
    	return usuario;
    }
    
    public void setRole(String role) {
    	this.role.set(role);;
    }
    
    public StringProperty getRole() {
    	return role;
    }
    
    public void setApellidoPaterno(String apellidoPaterno) {
    	this.apellidoPaterno.set(apellidoPaterno);;
    }
    
    public StringProperty getApellidoPaterno() {
    	return apellidoPaterno;
    }
    
    public void setApellidoMaterno(String apellidoMaterno) {
    	this.apellidoMaterno.set(apellidoMaterno);;
    }
    
    public StringProperty getApellidoMaterno() {
    	return apellidoMaterno;
    }
    
    public void setEdad(Integer edad) {
    	this.edad.set(edad);;
    }
    
    public IntegerProperty getEdad() {
    	return edad;
    }
    
    public void setSexo(String sexo) {
    	this.sexo.set(sexo);
    }
    public StringProperty getSexo() {
    	return sexo;
    }
    
    public void setId(Integer id) {
    	this.id.set(id);
    }
    public IntegerProperty getId() {
    	return id;
    }
    
    
}

