import java.util.ArrayList;

public class DevelopmentCard
{
    COLOR type;
    ArrayList<GemStack> coust;
    int prestigePoints;
    int level;
    
    COLOR getColor(){
        return type;
    }
    
    ArrayList<GemStack> getCoust(){
        return coust;
    }
    
    int getPrestigePoints(){
        return prestigePoints;
    }
    
    int getLevel(){
        return level;
    }
}
