
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 * Created by nadeeshani on 11/4/15.
 */

public class ReadXMLFile {
    
	public static void main(String argv[]) {
		try {
			File xmlFile = new File("src/Resources/staff.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nodeList = doc.getElementsByTagName("staff");
			
			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Node node = nodeList.item(temp);
				
				System.out.println("\nCurrent Element :" + node.getNodeName());

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.println("Staff id : " + element.getAttribute("id"));
					System.out.println("First Name : " + element.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println("Last Name : " + element.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Nick Name : " + element.getElementsByTagName("nickname").item(0).getTextContent());
					System.out.println("Salary : " + element.getElementsByTagName("salary").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
