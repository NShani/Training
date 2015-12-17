import java.util.Comparator;

public class StringNumberComparator implements Comparator<String>{
    public String name="nn";
    @Override
    public int compare(String first, String second) {
        //return Integer.parseInt(first) - Integer.parseInt(second);
        return Integer.compare(first.length(), second.length());
    }
}
