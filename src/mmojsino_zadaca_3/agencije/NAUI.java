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
public class NAUI extends Agency {

    private String agencyName = "NAUI";

    public static int[] levelsRecreational = {0, 1, 1, 1, 1, 0};

    public static int[] levelsProfessional = {1, 0, 1, 1, 0, 0, 0, 1};

    public static String[] levelsCertifiedRecreational = {"", "Scuba Diver", "Advanced Scuba Diver", "Scuba Rescue Diver",
        "Master Scuba Diver", ""};
    public static String[] levelsCertifiedProffesional = {"Assistant Instructor", "", "Divemaster", "Instructor",
        "", "", "", "Instructor Trainer"};

    public NAUI() {
        super(levelsRecreational, levelsProfessional, levelsCertifiedRecreational, levelsCertifiedProffesional);
    }

}
