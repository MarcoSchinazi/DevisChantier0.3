/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.VoitureDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.VoitureDuChantierSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class VoitureDuChantierDB {

    public static List<VoitureDuChantierDto> getAllVoitureDuChantier() throws DevisChantierDbException {
        List<VoitureDuChantierDto> elements = getCollection(new VoitureDuChantierSel(0));
        return elements;
    }

    public static List<VoitureDuChantierDto> getCollection(VoitureDuChantierSel sel) throws DevisChantierDbException {
        List<VoitureDuChantierDto> al = new ArrayList<>();
        try {
            String query = "Select idVoitureDuChantier, idChantier, idVoiture, debutDisponibilite, finDisponibilite, nombreJours  FROM VoitureDuChantier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            
            /*Pour une valeur numerique */
            if (sel.getIdVoitureDuChantier() != 0) {
                where = where + " idVoitureDuChantier = ? ";
            }
            
            /*if (sel.getDate() != null){
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " debutDisponibilite <= ?";
                where = where + " AND ";
                where = where + " finDisponibilite >= ?";
            }*/
                              
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdVoitureDuChantier() != 0) {
                    stmt.setInt(i, sel.getIdVoitureDuChantier());
                    i++;
                }
               /* if (sel.getDate() != null){
                    stmt.setDate(i, sel.getDate());
                    i++;
                    stmt.setDate(i, sel.getDate());
                }*/
            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new VoitureDuChantierDto(
                        rs.getInt("idVoitureDuChantier"), 
                        rs.getInt("idChantier"), 
                        rs.getInt("idVoiture"), 
                        rs.getDate("debutDisponibilite"),
                        rs.getDate("finDisponibilite"),
                        rs.getInt("nombreJours")
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de VoitureDuChantier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from VoitureDuChantier where idVoitureDuChantier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("VoitureDuChantier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(VoitureDuChantierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update VoitureDuChantier set "
                    + "idChantier=?, "
                    + "idVoiture=?, "
                    + "debutDisponibilite=?, "
                    + "finDisponibilite=?, "
                    + "nombreJours=? "
                    + "where idVoitureDuChantier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setInt(1, el.getIdChantier());
            update.setInt(2, el.getIdVoiture());
            update.setDate(3, el.getDebutDisponilibite());
            update.setDate(4, el.getFinDisponilibite());
            update.setInt(5, el.getNombreJours());
            update.setInt(6, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("VoitureDuChantier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(VoitureDuChantierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.VOITUREDUCHANTIER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into VoitureDuChantier(idVoitureDuChantier, idChantier, idVoiture, debutDisponibilite, finDisponibilite, nombreJours) "
                    + "values(?, ?, ?, ?, ?, ?)");
            insert.setInt(1, num);
            insert.setInt(2, el.getIdChantier());
            insert.setInt(3, el.getIdVoiture());
            insert.setDate(4, el.getDebutDisponilibite());
            insert.setDate(5, el.getFinDisponilibite());
            insert.setInt(6, el.getNombreJours());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("VoitureDuChantier: ajout impossible\n" + ex.getMessage());
        }
    }
}
