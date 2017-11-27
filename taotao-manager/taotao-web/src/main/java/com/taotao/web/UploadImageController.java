package com.taotao.web;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.FtpUtil;

/**
 * ftp上传下载图片
 * <p>Title: FtpUtil</p>
 * <p>Description: </p>
 * <p>Company:</p> 
 * @author	dunef
 * @date	2017.11.11
 * @version 1.0
 */
@Controller
public class UploadImageController {

	@RequestMapping("/pic/upload")
	public void uploadImage(MultipartFile uploadFile,HttpServletResponse response){
		JSONObject json = new JSONObject();
		String uuid = UUID.randomUUID().toString();
		response.setContentType("text/html;charset=utf-8");
		//获取上传的图片的名称
		String oldName = uploadFile.getOriginalFilename();
		String  newName = uuid+oldName.substring(oldName.indexOf("."));	
		InputStream in;
		try {
			//获取当前时间
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			String hehe = format.format( date );
			in = new BufferedInputStream(uploadFile.getInputStream());
			//执行上传返回一个布尔类型的变量，对上传是否成功做判断
			boolean flag = FtpUtil.uploadFile("192.168.99.131", 21, "ftpuser", "123", "/html",hehe,newName, in);
			//System.out.println(flag);
			String[] s = hehe.split("-");
			//成功返回的json对象
			if(flag == true){
				json.put("error",0);
				json.put("url","http://192.168.99.131/"+hehe+"/"+newName);
				//System.out.println("http://192.168.136.129/"+hehe+"/"+newName);
				response.getWriter().printf(json.toString());
			}else{
				//失败返回的json对象
				json.put("error",1);
				json.put("message","上传失败");
				response.getWriter().printf(json.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
