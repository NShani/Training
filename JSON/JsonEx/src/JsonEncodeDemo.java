/**
 * Created by nadeeshani on 12/11/15.
 */
import org.json.simple.JSONObject;

public class JsonEncodeDemo {

    public static void main(String[] args){
        JSONObject obj = new JSONObject();

        obj.put("Name", "Nadeesahni");
        obj.put("Age", new Integer(23));
        obj.put("Weight", new Integer(43));

        System.out.print(obj);
    }

}
