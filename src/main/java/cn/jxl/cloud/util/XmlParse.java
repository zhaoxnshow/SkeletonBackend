package cn.jxl.cloud.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

//import com.common.object.SmsSendResponseObject;

/**
 * @description 解析xml字符串
 */
public class XmlParse {

	public void readStringXml(String xml) {
		Document doc = null;
		try {

			// 读取并解析XML文档

			// SAXReader就是一个管道，用一个流的方式，把xml文件读出来

			// SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档

			// Document document = reader.read(new File("User.hbm.xml"));

			// 下面的是通过解析xml字符串的

			doc = DocumentHelper.parseText(xml); // 将字符串转为XML

			Element rootElt = doc.getRootElement(); // 获取根节点

			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称

			Iterator iter = rootElt.elementIterator("user"); // 获取根节点下的子节点head

			// 遍历head节点

			while (iter.hasNext()) {

				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("title"); // 拿到head节点下的子节点title值

				System.out.println("title:" + title);

				Iterator iters = recordEle.elementIterator("script"); // 获取子节点head下的子节点script

				// 遍历Header节点下的Response节点

				while (iters.hasNext()) {

					Element itemEle = (Element) iters.next();

					String username = itemEle.elementTextTrim("username"); // 拿到head下的子节点script下的字节点username的值

					String password = itemEle.elementTextTrim("password");

					System.out.println("username:" + username);
					System.out.println("password:" + password);
				}
			}
			Iterator iterss = rootElt.elementIterator("body"); /// 获取根节点下的子节点body

			// 遍历body节点

			while (iterss.hasNext()) {

				Element recordEless = (Element) iterss.next();
				String result = recordEless.elementTextTrim("result"); // 拿到body节点下的子节点result值

				System.out.println("result:" + result);

				Iterator itersElIterator = recordEless.elementIterator("form"); // 获取子节点body下的子节点form

				// 遍历Header节点下的Response节点

				while (itersElIterator.hasNext()) {

					Element itemEle = (Element) itersElIterator.next();

					String banlce = itemEle.elementTextTrim("banlce"); // 拿到body下的子节点form下的字节点banlce的值

					String subID = itemEle.elementTextTrim("subID");

					System.out.println("banlce:" + banlce);
					System.out.println("subID:" + subID);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * @description 将xml字符串转换成map
	 * @param xml
	 * @return Map
	 */
	public static Map readStringXmlOut(String xml) {
		Map map = new HashMap();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML

			Element rootElt = doc.getRootElement(); // 获取根节点

			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称

			Iterator iter = rootElt.elementIterator("authenticationSuccess"); // 获取根节点下的子节点head

			// 遍历head节点

			while (iter.hasNext()) {

				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("user"); // 拿到head节点下的子节点title值

				System.out.println("user:" + title);
				map.put("user", title);

				Iterator iters = recordEle.elementIterator("attributes"); // 获取子节点head下的子节点script

				// 遍历Header节点下的Response节点

				while (iters.hasNext()) {

					Element itemEle = (Element) iters.next();

					String username = itemEle.elementTextTrim("uid"); // 拿到head下的子节点script下的字节点username的值

					String password = itemEle.elementTextTrim("eduPersonAffiliation");

					System.out.println("username:" + username);
					System.out.println("password:" + password);
					map.put("username", username);
					map.put("password", password);

				}
			}

			Iterator iterss = rootElt.elementIterator("body"); /// 获取根节点下的子节点body

			// 遍历body节点

			while (iterss.hasNext()) {
				Element recordEless = (Element) iterss.next();
				String result = recordEless.elementTextTrim("result"); // 拿到body节点下的子节点result值

				System.out.println("result:" + result);

				Iterator itersElIterator = recordEless.elementIterator("form"); // 获取子节点body下的子节点form

				// 遍历Header节点下的Response节点

				while (itersElIterator.hasNext()) {

					Element itemEle = (Element) itersElIterator.next();

					String banlce = itemEle.elementTextTrim("banlce"); // 拿到body下的子节点form下的字节点banlce的值

					String subID = itemEle.elementTextTrim("subID");

					System.out.println("banlce:" + banlce);
					System.out.println("subID:" + subID);
					map.put("result", result);
					map.put("banlce", banlce);
					map.put("subID", subID);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * @description 将xml字符串转换成map
	 * @param xml
	 * @return Map
	 */
	public static String readUserFromXml(String xml) {
		String user = null;
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML

			Element rootElt = doc.getRootElement(); // 获取根节点

			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称

			Iterator iter = rootElt.elementIterator("authenticationSuccess"); // 获取根节点下的子节点head

			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				user = recordEle.elementTextTrim("user"); // 拿到head节点下的子节点title
				break;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public static void parse(String xml) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML

			Element rootElt = doc.getRootElement(); // 获取根节点smsReport

			Iterator iters = rootElt.elementIterator("sendResp"); // 获取根节点下的子节点sms

			while (iters.hasNext()) {
				Element recordEle1 = (Element) iters.next();
				Iterator iter = recordEle1.elementIterator("sms");
				int i = 0;
				// 遍历sms节点

				while (iter.hasNext()) {
					Element recordEle = (Element) iter.next();
					// SmsSendResponseObject r = new SmsSendResponseObject();
					String phone = recordEle.elementTextTrim("phone"); // 拿到sms节点下的子节点stat值

					String smsID = recordEle.elementTextTrim("smsID"); // 拿到sms节点下的子节点stat值

					System.out.println(phone + "===" + smsID);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// 下面是需要解析的xml字符串例子

		String xmlString0 = "<html>" + "<head>" + "<title>dom4j解析一个例子</title>" + "<script>"
				+ "<username>yangrong</username>" + "<password>123456</password>" + "</script>" + "</head>" + "<body>"
				+ "<result>0</result>" + "<form>" + "<banlce>1000</banlce>" + "<subID>36242519880716</subID>"
				+ "</form>" + "</body>" + "</html>";
		String xmlString = "<cas:serviceResponse xmlns:cas='http://www.yale.edu/tp/cas'>\n"
				+ "    <cas:authenticationSuccess>\n"
				+ "        <cas:user>{&#034;deprecated_id&#034;:34646181,&#034;email&#034;:&#034;zhaoxnshow@11185.cn&#034;,&#034;locked&#034;:false,&#034;phone&#034;:&#034;18641180165&#034;,&#034;user_id&#034;:&#034;34646181&#034;,&#034;user_name&#034;:&#034;zhaoxnshow@11185.cn&#034;}</cas:user>\n"
				+ "        <!-- 这段 -->  \n" + "          \n" + "            <cas:attributes>  \n"
				+ "                  \n" + "                    <cas:uid>uid</cas:uid>  \n" + "                  \n"
				+ "                    <cas:eduPersonAffiliation>eduPersonAffiliation</cas:eduPersonAffiliation>  \n"
				+ "                  \n" + "                    <cas:memberOf>[faculty, staff, org]</cas:memberOf>  \n"
				+ "                  \n"
				+ "                    <cas:groupMembership>groupMembership</cas:groupMembership>  \n"
				+ "                  \n" + "            </cas:attributes>  \n" + "          \n"
				+ "        <!-- 这段 end--> \n" + "        \n" + "        \n" + "    </cas:authenticationSuccess>\n"
				+ "</cas:serviceResponse>";

		/*
		 * Test2 test = new Test2(); test.readStringXml(xmlString);
		 */
		Map map = readStringXmlOut(xmlString);
		Iterator iters = map.keySet().iterator();
		while (iters.hasNext()) {
			String key = iters.next().toString(); // 拿到键

			String val = map.get(key).toString(); // 拿到值

			System.out.println(key + "=" + val);
		}
		String xml = "<batchSendResp><sendResp><sms><phone>137000000</phone><smsID>ff8080813349da9001334f0eed8c5923</smsID></sms></sendResp><sendResp><sms><phone>187000000</phone><smsID>ff8080813349da9001334f0eee045924</smsID></sms></sendResp></batchSendResp>";
		parse(xml);
	}

}
