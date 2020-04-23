package lepandthebugs.utils;

import lepandthebugs.model.BugModel;
import lepandthebugs.model.GameLevel;
import lepandthebugs.model.GrassModel;
import lepandthebugs.physics.GameConstant;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Keshan De Silva
 */
public class ResourceReader
{
    private static final ArrayList<String> levelInfo = new ArrayList<>();
    
    static
    {
        levelInfo.add("2,0,0#5,10,0!15,200,1!22,200,2#90");
        levelInfo.add("2,0,0!1,1,0!1,1,10#5,10,1!15,500,0!22,200,2#80");
        levelInfo.add("2,0,0!1,1,0!1,0,10#5,10,1!5,50,1!15,400,0!22,100,2#80");
        levelInfo.add("2,0,0!2,1,0!1,0,15#15,10,1!5,500,0!22,200,2!25,200,0#80");
        levelInfo.add("2,0,0!1,1,10!2,1,0!0,0,30#15,10,1!15,450,0!22,10,2#90");
        levelInfo.add("2,0,0!2,0,0!2,1,0!2,1,0!2,1,0#30,0,1#60");
        levelInfo.add("1,0,0!1,1,10!1,0,20!0,1,0!0,0,15!0,1,25#30,0,2!25,100,1#120");
        levelInfo.add("1,0,0!1,1,2!1,0,4!#30,0,2!25,400,0#30");
        levelInfo.add("1,0,0!1,1,0!1,0,10!1,1,10!1,0,15!1,1,15!#10,0,2!10,300,0!25,100,1#150");
        levelInfo.add("1,0,5!1,1,10!1,0,15!0,1,8!0,0,14!0,1,24#30,0,2!25,100,1#90");
    }
    
    public static ArrayList<GameLevel> getAllGameLevels()
    {
        ArrayList<GameLevel> gameLevelList = new ArrayList<>();
        
        try
        {
            // Read level info
            //String line;
            //File file = new File("C:\\custome_level.info");
            //BufferedReader input =  new BufferedReader(new FileReader(file));
            //while ((line = input.readLine()) != null)
            //{
            for (int i = 0; i < levelInfo.size(); i++)
            {
                String[] segments = levelInfo.get(i).split("#");
                if (segments.length == 3)
                {
                    ArrayList<BugModel> bugModelList = new ArrayList<>();
                    String[] bugs = segments[0].split("!");
                    for (String bugString : bugs)
                    {
                        String[] bugStringParm = bugString.split(",");
                        BugModel bugModel = new BugModel(
                                GameConstant.BugDirection.values()[Integer.parseInt(bugStringParm[0])],
                                GameConstant.BugLine.values()[Integer.parseInt(bugStringParm[1])]);
                        
                        bugModel.setStartTime(Integer.parseInt(bugStringParm[2]) * 1000);
                        bugModelList.add(bugModel);
                    }
                    
                    ArrayList<GrassModel> grassModelList = new ArrayList<>();
                    String[] grasses = segments[1].split("!");
                    for (String grassString : grasses)
                    {
                        String[] grassStringParm = grassString.split(",");
                        int level = GameConstant.GRASS_GROUND_LEVEL_1;
                        
                        if (Integer.parseInt(grassStringParm[2]) == 2)
                        {
                            level = GameConstant.GRASS_GROUND_LEVEL_3;
                        }
                        else if (Integer.parseInt(grassStringParm[2]) == 1)
                        {
                            level = GameConstant.GRASS_GROUND_LEVEL_2;
                        }
                        
                        GrassModel grassModel = new GrassModel(
                                Integer.parseInt(grassStringParm[0]), 
                                Integer.parseInt(grassStringParm[1]), level);
                        grassModelList.add(grassModel);
                    }
                    
                    gameLevelList.add(new GameLevel(i, bugModelList, grassModelList,
                            Integer.parseInt(segments[2])));
                }    
            }  
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
     
        return gameLevelList;    
    }
}
