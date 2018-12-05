package com.sjc.msm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class GetYzm {

	public static void main(String[] args) {
		getCode("18813008179");
	}
	
	private static final String URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";//发送短息
//	private static final String URL = "https://api.miaodiyun.com/20150822/query/accountInfo";//获取用户信息地址
	private static final String ACCOUNT_SID = "11b2907954dd454099a07ab12690e8b5";
	private static final String AUTH_TOKEN = "751c9655efd94e959210b1e6a42e2503";
	
	public static String getCode(String phone){
		
		//================�时间戳=======================
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) ;
		
		//================����ǩ��=======================
		String sigs = ACCOUNT_SID + AUTH_TOKEN + timestamp ;
		StringBuilder sig= new StringBuilder();
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] bytes = digest.digest(sigs.getBytes());
			for (byte b : bytes) {
				String hex = Integer.toHexString(b&0xff);
				if(hex.length()==1){
					sig.append("0"+hex);
				}else{
					sig.append(hex);
				}
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//=================获取验证码===========================
		String yzm = getYzm();
		//==================发送内容================================
		String smsContent = "【java视图】您的验证码为"+yzm+"，请于2分钟内正确输入，如非本人操作，请忽略此短信。";
		//===================������֤��==============================
		OutputStreamWriter osw = null;
		BufferedReader br = null;
		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");//����ʽ
			connection.setDoInput(true);//设置允许输入
			connection.setDoOutput(true);//设置允许输入
			connection.setConnectTimeout(5000);//设置链接时间
			connection.setReadTimeout(10000);//设置读取参数时间
			//设置相应头
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");//
			//�ύ����
			osw = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			
			String arge = getQueryArgs(ACCOUNT_SID,phone,smsContent,timestamp,sig.toString());
			osw.write(arge);
			osw.flush();
			
			br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String temp = "";
			while ((temp=br.readLine())!=null) {
				result.append(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result.toString());
		
		JSONObject json = new JSONObject(result.toString());
		String respCode = json.getString("respCode");
		if("00000".equals(respCode)){
			return yzm;
		}else{
			return "发送信息失败";
		}
	}
	
	/**
	 * post请求参数
	 * @return
	 */
	public static String getQueryArgs(String accountSid,String phone,String smsContent,String timestamp,String sig){
		
		StringBuilder pase = new StringBuilder();
		pase.append("accountSid=");
		pase.append(accountSid);
		pase.append("&smsContent=");
		pase.append(smsContent);
		pase.append("&to=");
		pase.append(phone);
		pase.append("&timestamp=");
		pase.append(timestamp);
		pase.append("&sig=");
		pase.append(sig);
		pase.append("&respDataType=JSON");
		
		return pase.toString();
	}
	
	/**
	 * 获取验证码
	 * @return
	 */
	public static String getYzm(){
		String yzm = new Random().nextInt(1000000)+"";
		if(yzm.length()!=6){
			return getYzm();
		}else{
			return yzm;
		}
	}
}
