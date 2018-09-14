package com.yunkouan.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Args;

import com.google.common.collect.Lists;

/** 
* @Description: HTTP接口方法
* @author tphe06
* @date 2018年4月18日 上午11:41:52  
*/
public final class HttpUtil {
	public static final int TIME_OUT = 15000;

	/** 
	* @Title: prepost 
	* @Description: 发送HTTP POST请求并返回COOKIE，通常用于登录
	* @auth tphe06
	* @time 2018 2018年3月17日 上午11:28:23
	* @param url HTTP URL
	* @param body HTTP BODY
	* @param cookie 返回的cookie存储在这里，不得为null
	* @return
	* @throws HttpException
	* @throws IOException
	* String
	*/
	public static String prepost(String url, String body, StringBuffer cookie) throws HttpException, IOException {
		HttpClient httpClient = new HttpClient();
		// 模拟登陆，按实际服务器端要求选用 Post或 Get请求方式
		PostMethod postMethod = new PostMethod(url);
		// 设置 HttpClient接收 Cookie,用与浏览器一样的策略
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		// 设置登录参数
		RequestEntity requestEntity = new StringRequestEntity(body,"application/json","UTF-8");
		postMethod.setRequestEntity(requestEntity);//postMethod.setRequestBody(body);
		httpClient.executeMethod(postMethod);
		// 获得登陆后的 Cookie
		Cookie[] cookies = httpClient.getState().getCookies();
		for (Cookie c : cookies) {
			cookie.append(c.toString() + ";");
		}
		// 返回数据
//		byte[] in = IOUtil.read(postMethod.getResponseBodyAsStream());
//		return new String(in, "UTF-8");
		return postMethod.getResponseBodyAsString();
	}
	/** 
	* @Title: post 
	* @Description: 携带COOKIE发送HTTP POST请求，可用于登录后权限控制的URL地址
	* @auth tphe06
	* @time 2018 2018年3月17日 上午11:30:26
	* @param url
	* @param cookies
	* @param body
	* @return
	* @throws HttpException
	* @throws IOException
	* String
	*/
	public static String post(String url, String cookies, String body) throws HttpException, IOException {
		// 进行登陆后的操作
		PostMethod postMethod = new PostMethod(url);
		RequestEntity requestEntity = new StringRequestEntity(body,"application/json","UTF-8");
		postMethod.setRequestEntity(requestEntity);
		// 每次访问需授权的网址时需带上前面的 cookie 作为通行证
		postMethod.setRequestHeader("cookie", cookies);
		// 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
		// 例如，referer 从哪里来的，UA 像搜索引擎都会表名自己是谁，无良搜索引擎除外
//		postMethod.setRequestHeader("Referer", "http://localhost:8080/telec/");
//		postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
		HttpClient httpClient = new HttpClient();
		httpClient.executeMethod(postMethod);
		// 返回数据
//		byte[] in = IOUtil.read(postMethod.getResponseBodyAsStream());
//		return new String(in, "UTF-8");
		return postMethod.getResponseBodyAsString();
	}

	/** 
	* @Title: get 
	* @Description: HTTP GET
	* @auth tphe06
	* @time 2018 2018年4月20日 上午11:21:38
	* @param url
	* @return
	* @throws ClientProtocolException
	* @throws IOException
	* String
	*/
	public static String get(String url) throws ClientProtocolException, IOException {
		String ret = Request.Get(url)
			.socketTimeout(TIME_OUT)
			.useExpectContinue()
			.version(HttpVersion.HTTP_1_1)
			.execute().returnContent().asString(Consts.UTF_8);
		return ret;
	}

	/** 
	* @Title: postXmlBody 
	* @Description: HTTP POST（用body传递xml参数）
	* @auth tphe06
	* @time 2018 2018年4月20日 上午10:53:00
	* @param url
	* @param xml
	* @return
	* @throws ClientProtocolException
	* @throws IOException
	* String
	*/
	public static String postXmlBody(String url, String xml) throws ClientProtocolException, IOException {
		String ret = Request.Post(url)
			.socketTimeout(TIME_OUT)
			.useExpectContinue()
			.version(HttpVersion.HTTP_1_1)
			.bodyString(xml, ContentType.create("application/x-www-form-urlencoded",Consts.UTF_8))
			.execute().returnContent().asString(Consts.UTF_8);
		return ret;
	}

	/** 
	* @Title: post 
	* @Description: HTTP POST（不建议使用）
	* @auth tphe06
	* @time 2018 2018年4月17日 下午6:38:14
	* @param url
	* @param nvps
	* @return
	* @throws ClientProtocolException
	* @throws IOException
	* String
	*/
//	private static String post(String url, List<NameValuePair> nvps) throws ClientProtocolException, IOException {
//		HttpClient httpclient = new DefaultHttpClient();
//		HttpPost httpPost = new HttpPost(url);
//		httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8")); 
//		HttpResponse response = httpclient.execute(httpPost);
//		HttpEntity entity = response.getEntity();
//		if(entity == null) return null;
//		String str = new String(EntityUtils.toString(entity));
//		return str;
//	}

	/** 
	* @Title: post 
	* @Description: HTTP POST（用参数传递）
	* @auth tphe06
	* @time 2018 2018年4月18日 上午11:36:25
	* @param url
	* @param nvps
	* @return
	* @throws ClientProtocolException
	* @throws IOException
	* String
	*/
	public static String post(String url, List<NameValuePair> nvps) throws ClientProtocolException, IOException {
		Content content = Request.Post(url)
	    .socketTimeout(TIME_OUT)
	    .bodyForm(nvps, Consts.UTF_8)
//	    .body(buildPostParam(nvps, null))
		.useExpectContinue()
		.version(HttpVersion.HTTP_1_1)
		.execute().returnContent();
//		System.out.println(content.getType());
		return content.asString(Consts.UTF_8);
	}
	/** 
	* @Title: post 
	* @Description: HTTP POST（用参数传递）
	* @auth tphe06
	* @time 2018 2018年4月18日 上午11:47:09
	* @param url
	* @param paramMap
	* @return
	* @throws ClientProtocolException
	* @throws IOException
	* String
	*/
	public static String post(String url, Map<String, String> paramMap) throws ClientProtocolException, IOException {
        if(MapUtils.isNotEmpty(paramMap)) {
            List<NameValuePair> paramList = Lists.newArrayListWithCapacity(paramMap.size());
            for (String key : paramMap.keySet()) {
                paramList.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            return post(url, paramList);
        }
		return post(url, Lists.newArrayListWithCapacity(0));
	}

    /** 
    * @Title: buildPostParam 
    * @Description: 构建POST方法请求参数
    * @auth tphe06
    * @time 2018 2018年4月18日 上午11:38:50
    * @param nameValuePairs
    * @param files
    * @return
    * HttpEntity
    */
	public static HttpEntity buildPostParam(List<NameValuePair> nameValuePairs, List<File> files) {
        if(CollectionUtils.isEmpty(nameValuePairs) && CollectionUtils.isEmpty(files)) return null;
        if(CollectionUtils.isNotEmpty(files)) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for (File file : files) {
                builder.addBinaryBody(file.getName(), file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
            }
            return builder.build();
        } else if(CollectionUtils.isNotEmpty(nameValuePairs)) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for (NameValuePair nameValuePair : nameValuePairs) {
                builder.addTextBody(nameValuePair.getName(), nameValuePair.getValue(), ContentType.create("application/json", Consts.UTF_8));
            }
            return builder.build();
        } else {
            return new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8);
        }
    }

    /** 
    * @Title: buildGetParam 
    * @Description: 构建GET方法请求参数
    * @auth tphe06
    * @time 2018 2018年4月18日 上午11:39:13
    * @param url
    * @param paramMap
    * @return
    * String
    */
	public static String buildGetParam(String url, Map<String, String> paramMap) {
        Args.notNull(url, "url");
        if(MapUtils.isNotEmpty(paramMap)) {
            List<NameValuePair> paramList = Lists.newArrayListWithCapacity(paramMap.size());
            for (String key : paramMap.keySet()) {
                paramList.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            //拼接参数
            return new StringBuilder(url).append("?").append(URLEncodedUtils.format(paramList, Consts.UTF_8)).toString();
        }
        return url;
    }
    /** 
    * @Title: buildGetParam 
    * @Description: 构建GET方法请求参数
    * @auth tphe06
    * @time 2018 2018年4月18日 上午11:39:28
    * @param url
    * @param paramList
    * @return
    * String
    */
	public static String buildGetParam(String url, List<NameValuePair> paramList) {
        Args.notNull(url, "url");
        //拼接参数
        return new StringBuilder(url).append("?").append(URLEncodedUtils.format(paramList, Consts.UTF_8)).toString();
    }
}