/**
 * Created by nadeeshani on 11/4/15.
 */

import java.util.ArrayList;
        import java.util.List;

        import org.xml.sax.Attributes;
        import org.xml.sax.SAXException;
        import org.xml.sax.helpers.DefaultHandler;



public class MyHandler extends DefaultHandler {

    //List to hold Employees object
    private List<Employee> empList = null;
    private Employee emp = null;

    boolean age = false;
    boolean name = false;
    boolean gender = false;
    boolean role = false;

    //getter method for employee list
    public List<Employee> getEmpList() {
        return empList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Employee")) {
            //create a new Employee and put it in Map
            String id = attributes.getValue("id");
            //initialize Employee object and set id attribute
            emp = new Employee();
            //emp.setId(Integer.parseInt(id));
            //initialize list
            if (empList == null){
                empList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("name")) {
            //set boolean values for fields, will be used in setting Employee variables
            name = true;
        } else if (qName.equalsIgnoreCase("age")) {
            age = true;
        } else if (qName.equalsIgnoreCase("gender")) {
            gender = true;
        } else if (qName.equalsIgnoreCase("role")) {
            role = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Employee")) {
            //add Employee object to list
            empList.add(emp);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (age) {
            //age element, set Employee age
            emp.setAge(Integer.parseInt(new String(ch, start, length)));
            age = false;
        } else if (name) {
            emp.setName(new String(ch, start, length));
            name = false;
        } else if (role) {
            emp.setRole(new String(ch, start, length));
            role = false;
        } else if (gender) {
            emp.setGender(new String(ch, start, length));
            gender = false;
        }
    }
}