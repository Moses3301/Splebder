import java.util.ArrayList;

public class DevelopmentCard
{
    COLOR type;
    ArrayList<GemStack> coust;
    int prestigePoints;
    
    COLOR getColor(){
        return type;
    }
    
    ArrayList<GemStack> getCoust(){
        return coust;
    }
    
    int getPrestigePoints(){
        return prestigePoints;
    }
}
