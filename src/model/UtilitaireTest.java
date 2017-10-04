/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.selDto.CamionDuChantierSel;
import junit.framework.*;

/**
 *
 * @author demaj
 */
public class UtilitaireTest extends TestCase {

    public void testCalculer() throws Exception {
        CamionDuChantierSel camcha = new CamionDuChantierSel(1);
        assertEquals(2, Utilitaire.montantCamions(camcha));

    }

}
