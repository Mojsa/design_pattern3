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
public class BSAC extends Agency {



    private String agencyName = "BSAC";

    public static int[] levelsRecreational = {0, 1, 1, 1, 1, 0};

    public static int[] levelsProfessional = {1, 0, 1, 1, 1, 0, 0, 0};

    public static String[] levelsCertifiedRecreational = {"", "Ocean Diver", "Ocean Diver", "Sports Diver",
        "Sports Diver", ""};
    public static String[] levelsCertifiedProffesional = {"Dive Leader", "", "Assistant Open Water Instructor", "Open Water Instructor",
        "Advanced Instructor", "", "", ""};

    public BSAC() {
        super(levelsRecreational, levelsProfessional, levelsCertifiedRecreational, levelsCertifiedProffesional);
    }


}
