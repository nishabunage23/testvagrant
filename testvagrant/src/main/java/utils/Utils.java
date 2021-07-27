package utils;
import com.sun.tools.javac.util.Assert;

public class Utils {

    public void compare(int fromUI, int FromAPI,int variance){
        int temDiff;
        if(fromUI>=FromAPI){
            temDiff = (fromUI-FromAPI);
        }else{
            temDiff = (FromAPI-fromUI);
        }
        if (temDiff>variance){
            Assert.error("Given values differ by more than specified limit");

        }else{
            System.out.println("Passed");
        }
    }

    public int getRoundOffValue(String rowValue){

        int tempFromAPI = Integer.parseInt(rowValue.substring(0, rowValue.indexOf(".")));
        return tempFromAPI;

    }
    public int convertKelvinToCelcius(int tempInKelvin){

        int tempInCelcius = (int) (tempInKelvin-273.15);
        return tempInCelcius;

    }
}
