/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.db;

import db.dto.CamionDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.CamionDuChantierSel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vali
 */
public class CamionDuChantierDB {

    public static List<CamionDuChantierDto> getAllCamionDuChantier() throws DevisChantierDbException {
        List<CamionDuChantierDto> elements = getCollection(new CamionDuChantierSel(0));
        return elements;
    }

    public static List<CamionDuChantierDto> getCollection(CamionDuChantierSel sel) throws DevisChantierDbException {
        List<CamionDuChantierDto> al = new ArrayList<>();
        try {
            String query = "Select idCamionDuChantier, idChantier, idCamion, nombreheures, debutdisponibilite, findisponibilite FROM CamionDuChantier ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            /*Pour une valeur numerique */
            if (sel.getIdCamionDuChantier() != 0) {
                where = where + " idCamionDuChantier = ? ";
            }
            if (sel.getIdChantier() != 0) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " idChantier = ? ";
            }

            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdCamionDuChantier() != 0) {
                    stmt.setInt(i, sel.getIdCamionDuChantier());
                    i++;
                }
                if (sel.getIdChantier() != 0) {
                    stmt.setInt(i, sel.getIdChantier());
                    i++;
                }
            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new CamionDuChantierDto(
                        rs.getInt("idCamionDuChantier"),
                        rs.getDate("debutDisponibilite"),
                        rs.getDate("finDisponibilite"),
                        rs.getDouble("nombreHeures"),
                        rs.getInt("idChantier"),
                        rs.getInt("idCamion")
                )
                );
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DevisChantierDbException("Instanciation de CamionDuChantier impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    public static void deleteDb(int id) throws DevisChantierDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from CamionDuChantier where idCamionDuChantier=" + id);
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("CamionDuChantier: suppression impossible\n" + ex.getMessage());
        }
    }

    public static void updateDb(CamionDuChantierDto el) throws DevisChantierDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update CamionDuChantier set "
                    + "idChantier=?, "
                    + "idCamion=?, "
                    + "nombreHeures=?, "
                    + "debutDisponibilite=?, "
                    + "finDisponibilite=? "
                    + "where idCamionDuChantier=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            update.setInt(1, el.getIdChantier());
            update.setInt(2, el.getIdCamion());
            update.setDouble(3, el.getNombreHeures());
            update.setDate(4, el.getDebutDisponibilite());
            update.setDate(5, el.getFinDisponibilite());
            update.setInt(6, el.getId());
            update.executeUpdate();
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("CamionDuChantier, modification impossible:\n" + ex.getMessage());
        }
    }

    public static int insertDb(CamionDuChantierDto el) throws DevisChantierDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.CAMIONDUCHANTIER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into CamionDuChantier(idCamionDuChantier, idChantier, idCamion, nombreHeures, debutDisponibilite, finDisponibilite) "
                    + "values(?, ?, ?, ?, ?, ?)");
            insert.setInt(1, num);
            insert.setInt(2, el.getIdChantier());
            insert.setInt(3, el.getIdCamion());
            insert.setDouble(4, el.getNombreHeures());
            insert.setDate(5, el.getDebutDisponibilite());
            insert.setDate(6, el.getFinDisponibilite());
            insert.executeUpdate();
            return num;
        } catch (DevisChantierDbException | SQLException ex) {
            throw new DevisChantierDbException("CamionDuChantier: ajout impossible\n" + ex.getMessage());
        }
    }
}
