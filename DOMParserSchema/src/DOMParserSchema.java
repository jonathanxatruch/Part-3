import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;

public class DOMParserSchema {
 public static void main(String[] args) {
  String schemaName = "music.xsd";
  String xmlName = "music.xml";
  Schema schema = loadSchema(schemaName);
  Document document = parseXmlDom(xmlName);
  
  if(document!=null)
	  validateXml(schema, document);
 }
 public static void validateXml(Schema schema, Document document) {
  try {
   Validator validator = schema.newValidator();
   System.out.println("Validator Class: " + validator.getClass().getName());
   validator.validate(new DOMSource(document));
   System.out.println("XML Validation Passed!");
  } catch (Exception e) {
   System.out.println(e.toString());
   System.out.println(" XML Validation Failed!");
  }
 }
 public static Schema loadSchema(String schemaFileName) {
  Schema schema = null;
  try {
   String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
   SchemaFactory factory = SchemaFactory.newInstance(language);
   schema = factory.newSchema(new File(schemaFileName));
  } catch (Exception e) {
   System.out.println(e.toString());
  }
  return schema;
 }
 public static Document parseXmlDom(String xmlName) {
  Document document = null;
  try {
   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
   DocumentBuilder builder = factory.newDocumentBuilder();
   document = builder.parse(new File(xmlName));
   System.out.println("XML Parsing Successful!");
  } catch (Exception e) {
   System.out.println(e.toString());
   System.out.println("XML Parsing Failed!");
  }
  return document;
 }
}
