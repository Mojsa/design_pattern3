/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3.agencije;

import java.util.Arrays;


/**
 *
 * @author Mario
 */
public class SSI extends Agency {

    
    private String agencyName = "SSI";

    public static int[] levelsRecreational = {1, 1, 1, 1, 1, 1};

    public static int[] levelsProfessional = {1, 1, 1, 1, 1, 1, 1, 1};

    public static String[] levelsCertifiedRecreational = {"Scuba Diver", "Open Water Diver", "Advanced Adventure", "Diver Stress & Rescue",
        "Advanced Open Water Diver", "Master Diver"};
    public static String[] levelsCertifiedProffesional = {"Dive Guide", "Divemaster", "Dive Control Specialist", "Open Water Instructor",
        "Advanced Open Water Instructor", "Divemaster Instructor", "Dive Control Specialist Instructor", "Instructor Trainer"};

    public SSI() {
        super(levelsRecreational, levelsProfessional, levelsCertifiedRecreational, levelsCertifiedProffesional);
    }

}
