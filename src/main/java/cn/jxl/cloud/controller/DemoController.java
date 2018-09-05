package cn.jxl.cloud.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			@RequestParam(value = "picStrBase64")  String picStrBase64, HttpServletRequest request,HttpServletResponse response) {
		System.out.println(new Date() + "picPathName=" + picPathName + "&picStrBase64=" + picStrBase64);
		System.out.println("request=" + request.getHeader("User-Agent"));
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
	
	@RequestMapping("redirectpdf")
//	public String redirectPdf(@PathVariable String pdfUrl) {
	public void redirectPdf(HttpServletRequest request, HttpServletResponse response) {

		String curOrigin = request.getHeader("Origin");
		System.out.println("request origin: " + curOrigin);

		response.setHeader("Access-control-Allow-Origin", curOrigin);
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
	
		System.out.println(333333333);
		String pdfUrl = "/Users/zhaoxn/post-online/git/vue-skeleton/static/img/AXIA280I4418PAAAAA19.pdf";
		File file = new File(pdfUrl);
		byte[] data = null;
		try {
			FileInputStream input = new FileInputStream(file);
			data = new byte[input.available()];
			input.read(data);
			response.getOutputStream().write(data);
			input.close();
		} catch (Exception e) {
			System.out.println("pdf文件处理异常：" + e.getMessage());
		}
		 
//		return "redirect:" + pdfUrl;
		// return "aaaaaa";
	}
	
	@RequestMapping("redirectpdf1")
//	public String redirectPdf(@PathVariable String pdfUrl) {
	public void redirectPdf1(HttpServletRequest request, HttpServletResponse response) {
		String curOrigin = request.getHeader("Origin");
		System.out.println("request origin: " + curOrigin);

		response.setHeader("Access-control-Allow-Origin", curOrigin);
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
		
		System.out.println("99999999999999999");
		String destUrl = "http://192.168.202.4/kf-cnt/policy/AXIA280I4418PAAAAA19.pdf";
//		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		
		int size = 0;
		try {
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
//			fos = new FileOutputStream("c:\\haha.gif");
			while ((size = bis.read(buf)) != -1) {
				response.getOutputStream().write(buf, 0, size);
			}
//			fos.flush();
		} catch (IOException e) {
		} catch (ClassCastException e) {
		} finally {
			try {
//				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
			} catch (NullPointerException e) {
			}
		}
		// return "redirect:" + pdfUrl;
		// return "aaaaaa";
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
