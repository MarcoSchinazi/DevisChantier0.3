/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.selDto;

/**
 *
 * @author Vali
 */
public class ClientSel {
    
    private int idClient;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private String email;

    public ClientSel(int idClient, String nom, String prenom, String numeroTelephone, String email) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTelephone = numeroTelephone;
        this.email = email;
    }

        public ClientSel(int idClient) {
        this.idClient = idClient;
    }
        
        public ClientSel(int idClient, String c) {
        this.idClient = idClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public String getEmail() {
        return email;
    }

    
}
