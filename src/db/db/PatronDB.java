/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.PatronDto;
import db.exception.DevisChantierDbException;
import db.selDto.PatronSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class PatronDB {

    public static List<PatronDto> getAllPatron() throws DevisChantierDbException {
        List<PatronDto> elements = getCollection(new PatronSel(0));
        return elements;
    }

    public static List<PatronDto> getCollection(PatronSel sel) throws DevisChantierDbException {
        List<PatronDto> al = new ArrayList<>();
        try {
            String query = "Select idPatron, password, nom, prenom, dateNaissance, numeroTelephone, numeroTelephonePro, email FROM Patron ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdPatron() != 0) {
                where = where + " idPatron = ? ";
            }
            /*Pour une valeur string */
            if (sel.getPassword() != null && !sel.getPassword().equals("")) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " password like ? ";
            }
            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdPatron() != 0) {
                    stmt.setInt(i, sel.getIdPatron());
                    i++;
                }

            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new PatronDto(
                        rs.getInt("idPatron"), 
                        rs.getString("password"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateNaissance"),
                        rs.getString("numeroTelephone"), 
                        rs.getString("numeroTelephonePro"),   
                        rs.getString("email")
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de Patron impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Patron where idPatron=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Patron: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(PatronDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update Patron set "
                    + "password=? "
                    + "nom=? "
                    + "prenom=? "
                    + "dateNaissance=? "
                    + "numeroTelephone=? "
                    + "numeroTelephonePro=? "
                    + "email=? "
                    + "where idPatron=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setString(1, el.getPassword());
            update.setString(2, el.getNom());
            update.setString(3, el.getPrenom());
            update.setDate(4, el.getDateNaissance());
            update.setString(5, el.getNumeroTelephone());
            update.setString(6, el.getNumeroTelephonePro());
            update.setString(7, el.getEmail());
            update.setInt(8, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Patron, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(PatronDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.PATRON);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Patron(idPatron, password, nom, prenom, dateNaissance, numeroTelephone, numeroTelephonePro, email) "
                    + "values(?, ?, ?)");
            insert.setInt(1, num);
            insert.setString(2, el.getPassword());
            insert.setString(3, el.getNom());
            insert.setString(4, el.getPrenom());
            insert.setDate(5, el.getDateNaissance());
            insert.setString(6, el.getNumeroTelephone());
            insert.setString(7, el.getNumeroTelephonePro());
            insert.setString(8, el.getEmail());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("Patron: ajout impossible\n" + ex.getMessage());
        }
    }
}

