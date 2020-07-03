/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jose Pablo Sandoval
 */
public class Person_system {
    public String user;
    public float cambio;
    public String rol;
    public String Nombre;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String edad;
    public String sexo;
    public int id;
    
    public Person_system(){
        this.Nombre = "";
        this.rol = "";
        this.user = "";
        this.cambio = 0;
    }
    
    public Person_system(int id, String user, String Nombre,String apellidoPaterno,String apellidoMaterno, String edad,String sexo, String rol){
        this.id=id;
    	this.Nombre = Nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.edad = edad;
        this.sexo = sexo;
        this.user = user;
        this.rol = rol;
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

    public void setApellidoPaterno(String apellidoPaterno) {
    	this.apellidoPaterno = apellidoPaterno;
    }
    
    public String getApellidoPaterno() {
    	return apellidoPaterno;
    }
    
    public void setApellidoMaterno(String apellidoMaterno) {
    	this.apellidoMaterno = apellidoMaterno;
    }
    
    public String getApellidoMaterno() {
    	return apellidoMaterno;
    }
    
    public void setEdad(String edad) {
    	this.edad=edad;
    }
    
    public String getEdad() {
    	return edad;
    }
    
    public void setSexo(String sexo) {
    	this.sexo=sexo;
    }
    public String getSexo() {
    	return sexo;
    }
    
    public void setId(int id) {
    	this.id=id;
    }
    public int getId() {
    	return id;
    }
    
    
}

