package com.cn.myhttptest;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.*;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;


import javax.net.ssl.SSLContext;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

/**
 * chen</br>
 * 2016年1月27日 上午9:31:48</br>
 *
 * @description
 *
 */
public class HttpClientUtil {

	private final static Logger logger = Logger.getLogger(HttpClientUtil.class.toString());
	private static PoolingHttpClientConnectionManager connManager = null;
	private static CloseableHttpClient httpclient = null;
	private static int timeout = 55000;

	static {
		try {
			RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder
					.<ConnectionSocketFactory> create();
			ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
			registryBuilder.register("http", plainSF);
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			TrustStrategy anyTrustStrategy = new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			};
			SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, anyTrustStrategy)
					.build();

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", new SSLConnectionSocketFactory(sslContext)).build();

			connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			// Create socket configuration
			SocketConfig socketConfig = SocketConfig.custom().setRcvBufSize(2048).setSndBufSize(2048)
					.setSoKeepAlive(true).setTcpNoDelay(true).setSoTimeout(2*60*1000).build();
			connManager.setDefaultSocketConfig(socketConfig);
			// Create message constraints
			MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200)
					.setMaxLineLength(2000).build();
			// Create connection configuration
			ConnectionConfig connectionConfig = ConnectionConfig.custom()
					.setMalformedInputAction(CodingErrorAction.IGNORE)
					.setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8)
					.setMessageConstraints(messageConstraints).build();
			connManager.setDefaultConnectionConfig(connectionConfig);
			connManager.setMaxTotal(60000);
			connManager.setDefaultMaxPerRoute(120);
			httpclient = HttpClients.custom().setConnectionManager(connManager).build();

		} catch (KeyManagementException e) {
		} catch (NoSuchAlgorithmException e) {
//			eyStoreException e) {
//			logger.error("",e);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}


	public static String invokeGet(String url, Map<String, String> params, String encode, int connectTimeout) {
		String responseString = null;
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connectTimeout)
				.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();

		StringBuilder sb = new StringBuilder();
		sb.append(url);
		int i = 0;
		for (Entry<String, String> entry : params.entrySet()) {
			if (i == 0 && !url.contains("?")) {
				sb.append("?");
			} else {
				sb.append("&");
			}
			sb.append(entry.getKey());
			sb.append("=");
			String value = entry.getValue();
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.info("encode http get params error, value is " + value);
				sb.append(URLEncoder.encode(value));
			}
			i++;
		}
		logger.info("[HttpUtils Get] begin invoke:" + sb.toString());
		HttpGet get = new HttpGet(sb.toString());
		get.setConfig(requestConfig);

		try {
//			setSessionId(get);
			CloseableHttpResponse response = httpclient.execute(get);
			try {
				HttpEntity entity = response.getEntity();
				try {
					if (entity != null) {
						responseString = EntityUtils.toString(entity, encode);
					}
				} finally {
					if (entity != null) {
						entity.getContent().close();
					}
				}
			} catch (Exception e) {
//				logger.error(String.format("[HttpUtils Get]get response error, url:%s", sb.toString()), e);
				return responseString;
			} finally {
				if (response != null) {
					response.close();
				}
			}
			logger.info(String.format("[HttpUtils Get]Debug url:%s , response string %s:", sb.toString(), responseString));
		} catch (SocketTimeoutException e) {
			logger.throwing(String.format("[HttpUtils Get]invoke get timout error, url:%s", sb.toString()),"", e);
			return responseString;
		} catch (Exception e) {
			logger.info(String.format("[HttpUtils Get]invoke get error, url:%s", sb.toString()));
		} finally {
			get.releaseConnection();
		}
		return responseString;
	}



	/*public static String proxyHttpGet(String url, Map<String, String> params, int timeout) {
//		String proxy_ip = PropertiesLoader.getServerConfMap().get(ServerConfig.ProxyServerkey.SERVER_IP);
//
//		int proxy_port = Integer.parseInt(PropertiesLoader.getServerConfMap().get(ServerConfig.ProxyServerkey.SERVER_PORT));



		String content = null;


		StringBuilder sb = new StringBuilder();
		sb.append(url);
		int i = 0;
		for (Entry<String, String> entry : params.entrySet()) {
			if (i == 0 && !url.contains("?")) {
				sb.append("?");
			} else {
				sb.append("&");
			}
			sb.append(entry.getKey());
			sb.append("=");
			String value = entry.getValue();
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				Console.error("","encode http get params error, value is " + value, e);
				return null;
			}
			i++;
		}
		Console.info("[HttpUtils Get] begin the proxy server is :　"+proxy_ip+": "+proxy_port+" invoke:" ,sb.toString());
		CloseableHttpClient closeableHttpClient = null;
		try {
			closeableHttpClient = HttpClientBuilder.create().build();
			HttpHost proxy = new HttpHost(proxy_ip, proxy_port);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).setSocketTimeout(timeout).setConnectTimeout(timeout)
					.setConnectionRequestTimeout(timeout).setExpectContinueEnabled(false).build();
			HttpGet httpPost = new HttpGet(sb.toString());
			httpPost.setConfig(config);
			setSessionId(httpPost);
			HttpResponse response = closeableHttpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity);
			Console.info("the response is ",content);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeableHttpClient.close();	//关闭连接
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}*/


	/*private static void setSessionId(HttpGet get) {
		*//*InheritableThreadLocal*//*
		String sessionId = SessionThreadLocal.getSessionId();
		if (sessionId == null || sessionId.trim().length() == 0) {
			logger.error("sessionid filter make no sense.please check CAHttpClient demo.");
		} else {
			logger.info("thread id " + Thread.currentThread().getId() + " with session " + sessionId);
			*//*特别注意，当使用线程池的时候，线程池中的线程不能修改透传的值*//*
			// SessionThreadLocal.setSessionId(sessionId + "changed");
		}
        *//*InheritableThreadLocal*//*
		get.setHeader(JessionId,sessionId);
	}*/
/*
	private static void setSessionId(HttpPost post) {
		        *//*InheritableThreadLocal*//*
		String sessionId = SessionThreadLocal.getSessionId();
		if (sessionId == null || sessionId.trim().length() == 0) {
			logger.error("sessionid filter make no sense.please check CAHttpClient demo.");
		} else {
			logger.info("session id is " + sessionId);
		}
        *//*InheritableThreadLocal*//*
		post.setHeader(JessionId,sessionId);
	}*/
}
