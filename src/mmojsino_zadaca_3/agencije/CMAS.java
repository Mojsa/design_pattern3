/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3.agencije;


/**
 *
 * @author Mario
 */
public class CMAS extends Agency {

    private String agencyName = "CMAS";

    public static int[] levelsRecreational = {0, 1, 1, 1, 1, 0};

    public static int[] levelsProfessional = {1, 0, 1, 1, 0, 0, 0, 0};

    public static String[] levelsCertifiedRecreational = {"", "One Star Diver", "One Star Diver", "Two Star Diver",
        "Two Star Diver", ""};
    public static String[] levelsCertifiedProffesional = {"Three Star Diver", "", "One Star Instructor", "Two Star Instructor",
        "", "", "", ""};

    public CMAS() {
        super(levelsRecreational, levelsProfessional, levelsCertifiedRecreational, levelsCertifiedProffesional);
    }

}
