package com.kawa.jd.util;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
@Component
public class JDUtil {
private PoolingHttpClientConnectionManager cManager = null;
	
	public JDUtil () {
		cManager = new PoolingHttpClientConnectionManager();
		cManager.setMaxTotal(100);
		cManager.setDefaultMaxPerRoute(10);
	}
	public CloseableHttpClient getClient(PoolingHttpClientConnectionManager cManager) {
		return HttpClients.custom().setConnectionManager(cManager).build();
	}
	public RequestConfig getConfig() {
		return RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(2000)
		.setSocketTimeout(2000).build();
	}
	public String doGetHtml(String url) {
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cManager).build();
		HttpGet get = new HttpGet(url);
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
        // 传输的类型
		get.addHeader("Content-Type", "application/x-www-form-urlencoded");
		get.setConfig(getConfig());
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
			if(response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String html = EntityUtils.toString(entity);
				return html;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(response!=null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public String saveImage(String imagePath) {
		CloseableHttpClient httpClient = getClient(cManager);
		HttpGet get = new HttpGet(imagePath);
		CloseableHttpResponse response = null;
		//保存图片的文件夹
		String dir = "D:/Download/image/";
		try {
			response = httpClient.execute(get);
			if(response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String imageName = UUID.randomUUID().toString()+".jpg";
				FileOutputStream ous = new FileOutputStream(dir+imageName);
				entity.writeTo(ous);
				return dir+imageName;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(response!=null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
