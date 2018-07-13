package cn.jxl.cloud.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jxl.cloud.entry.User;
import cn.jxl.cloud.util.HttpDemoUtil;
import cn.jxl.cloud.util.ImageBase64Util;
import cn.jxl.cloud.util.XmlParse;

@RestController
@RequestMapping("/api")
public class DemoController {

//	@RequestMapping("validatest/{url}/{service}/{ticket}")
//	public String validateST(@PathVariable String url, @PathVariable String service, @PathVariable String ticket) {
////	@RequestMapping("validatest/{ticket}")
////	public String validateST(@PathVariable String ticket) {
////		String url = "https://passport.11185.cn:8001/cas/serviceValidate";
////		HashMap<String,String> map = new HashMap<String,String>();
////		map.put("service", "http://localhost:8081/ssocatcher?backRouter=/");
////		map.put("ticket", ticket);
//		System.out.println(url);
//		System.out.println(service);
//		System.out.println(ticket);
//		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("service", service);
//		map.put("ticket", ticket);
//
//		String rtn = null;
//		try {
//			rtn = HttpUtilDemo.get(url, map);
//			System.out.println(rtn);
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		return rtn;
//	}
	
	
	@RequestMapping("validatest")
	public String validateST(@RequestHeader(value = "url")  String url,
			@RequestHeader(value = "service")  String service, @RequestHeader(value = "ticket")  String ticket) {
		System.out.println(url);
		System.out.println(service);
		System.out.println(ticket);
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("service", service);
		map.put("ticket", ticket);

		String rtn = null;
		try {
			rtn = XmlParse.readUserFromXml(HttpDemoUtil.get(url, map));
			
			System.out.println(rtn);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return rtn;
	}
	
	@RequestMapping("validate-st-g")
	public String validateSTG(@RequestHeader(value = "url")  String url,
			@RequestHeader(value = "service")  String service, @RequestHeader(value = "ticket")  String ticket)
			throws IOException {
		
		System.out.println(url + "---->"+ service + "--->" +ticket);
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("service", service.replaceAll("\"", ""));
		map.put("ticket", ticket.replaceAll("\"", ""));
		
		String rtn = HttpDemoUtil.get(url.replaceAll("\"", ""), map);
		System.out.println(rtn);
		return "helllo";
	}
	
	@RequestMapping(path = "validate-st-p", method = RequestMethod.POST)
	public String validateSTP(@RequestParam(value = "url")  String url,
			@RequestParam(value = "service")  String service, @RequestParam(value = "ticket")  String ticket) {
		 
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("service", service);
		map.put("ticket", ticket);
		
		String rtn = "";
		try {
			rtn = HttpDemoUtil.get(url, map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rtn.toString();
	}

	@RequestMapping(path = "savePictureByBase64", method = RequestMethod.POST)
	public String savePictureByBase64(@RequestParam(value = "picPathName")  String picPathName,
			@RequestParam(value = "picStrBase64")  String picStrBase64) {
		System.out.println(new Date() + "picPathName=" + picPathName + "&picStrBase64=" + picStrBase64);
		StringBuffer rtn = new StringBuffer();
		String[] picStrArr = null;
		try {
			if(picStrBase64.isEmpty()) {
				throw new Exception("数据为空。");
			}
			picStrArr = picStrBase64.split("base64,");
			ImageBase64Util.GenerateImage(picPathName, picStrArr[picStrArr.length - 1]);
			rtn.append("0:成功");
			System.out.println(rtn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rtn.append("1:失败:");
			rtn.append(e.getMessage());
		}

		return rtn.toString();
	}
	
	@RequestMapping("find")
	public Object find() {
		User user = new User();
		user.setId("1");
		user.setName("张三");
		user.setAge("25");
		return user;
	}

	@RequestMapping("get/{name}")
	public Map<String, Object> get(@PathVariable String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("value", "Hello");
		return map;
	}

//	@RequestMapping("ratings")
//	public Object ratings() {
//		JSONObject jo = JSONObject.fromObject(IndexController.ratings);
//		JSONObject rtnJo = new JSONObject();
//		rtnJo.put("errno", "0");
//		rtnJo.put("data", jo);
//		return rtnJo;
//	}

//	@RequestMapping(path = "ratings/str", method = RequestMethod.POST)
//	public String ratingsStr(@RequestParam String username, @RequestParam String password) {
//		JSONObject jo = JSONObject.fromObject(IndexController.ratings);
//		JSONObject rtnJo = new JSONObject();
//		JSONObject dataO = new JSONObject();
//		dataO.put("username", username);
//		dataO.put("password", password);
//		rtnJo.put("errno", "0");
//		rtnJo.put("data", dataO);
//		return rtnJo.toString();
//	}

//	public static void main(String[] args) {
//		JSONObject jo = JSONObject.fromObject(IndexController.ratings);
//		JSONObject rtnJo = new JSONObject();
//		rtnJo.put("errno", "0");
//		rtnJo.put("data", jo);
//		System.out.println(rtnJo);
//		System.out.println(rtnJo.toString());
//		System.out.println(String.valueOf(rtnJo));
//		System.out.println("zhaoxn");
//
//	}

}
