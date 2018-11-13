package com.jaswine.pblog;


import com.jaswine.pblog.api.TokenAPI;
import com.jaswine.pblog.beans.result.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;

/**
 * AccessToken管理器
 * <p>访问微信接口都需要带上access_token,使用AccessTokenManager来统一管理AccessToken</p>
 * @author Jaswine
 */
public class AccessTokenManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenManager.class);


	/**
	 * 定期执行任务,来完成token的刷新
	 */
	private static ScheduledExecutorService scheduledExecutorService;
	/**
	 * token集合
	 */
	private static Map<String,String> tokenMap = new ConcurrentHashMap<>();
	/**
	 * scheduledFuture集合
	 */
	private static Map<String, ScheduledFuture<?>> futureMap  = new ConcurrentHashMap<>();
	/**
	 * 线程池大小
	 */
	private static int poolsize = 2;
	/**
	 * 是否守护线程
	 */
	private static boolean daemon = Boolean.TRUE;
	/**
	 * 默认appid
	 */
	private static String defaultAppid;


	/**
	 * 初始化 scheduledExecutorService
	 */
	private static void initScheduledExecutorService(){
		scheduledExecutorService = Executors.newScheduledThreadPool(poolsize, new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = Executors.defaultThreadFactory().newThread(r);
				// 设置守护线程
				thread.setDaemon(daemon);
				return thread;
			}
		});
		LOGGER.info("[AccessTokenManager]是否开启守护线程:"+daemon+";线程池大小:"+poolsize);
	}

	/**
	 * 初始化token刷新参数(默认形式,每118分钟刷新一次)
	 * @param appid appid
	 * @param appsecret appsecret
	 */
	public void init(final String appid,final String appsecret){
		init(appid,appsecret,0,60*118);
	}


	/**
	 * 初始化token属性,个性化参数配置
	 * @param appid appid
	 * @param appsecret appsecret
	 * @param initialDelay 首次执行延迟(秒/s)
	 * @param delay 执行间隔(秒/s)
	 */
	public void init(String appid, String appsecret, int initialDelay, int delay) {
		LOGGER.info("[AccessTokenManger]开始初始化");
		if (scheduledExecutorService == null){
			LOGGER.info("[AccessTokenManger]初始化定时刷新任务");
			initScheduledExecutorService();
		}
		if (defaultAppid == null){
			LOGGER.info("[AccessTokenManger]赋值默认appid:{"+appid+"}");
			defaultAppid = appid;
		}
		if (futureMap.containsKey(appid)){
			LOGGER.info("[AccessTokenManger]初始化futureMap");
			futureMap.get(appid).cancel(true);
		}
		if (initialDelay == 0){
			LOGGER.info("[AccessTokenManger]获得appid:{"+appid+"}的accesstoken");
			doRun(appid,appsecret);
		}

		ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				doRun(appid, appsecret);
			}
		}, initialDelay == 0 ? delay : initialDelay, delay, TimeUnit.SECONDS);

		futureMap.put(appid,scheduledFuture);
		LOGGER.info("[AccessTokenManager]已经将appid:{"+appid+"}加入到自动刷新任务中");
	}

	/**
	 * 刷新accesstoken
	 * @param appid appid
	 * @param appsecret appsecret
	 */
	private  void doRun(final String appid,final String appsecret){
		Token token = TokenAPI.getToken(appid, appsecret);
		if (token.getAccess_token() == null){
			LOGGER.error("[AccessTokenManager]请求accesstoken错误,错误码:{"+token.getErrcode()+"};错误信息:{"+ token.getErrmsg() +"}");
		}else {
			tokenMap.put(appid,token.getAccess_token());
			LOGGER.info("[AccessTokenManager]AccessToken已经刷新,appid:{"+appid+"}");
		}
	}

	/**
	 * 关闭 token 刷新功能(全部)
	 */
	public void destroyed(){
		scheduledExecutorService.shutdownNow();
		LOGGER.info("[AccessTokenManager]取消全局accesstoken自动刷新");
	}

	/**
	 * 关闭token刷新功能(特定)
	 * @param appid appid
	 */
	public void destroyed(String appid){
		if (futureMap.containsKey(appid)){
			futureMap.get(appid).cancel(true);
			LOGGER.info("[AccessTokenManager]取消appid:{"+appid+"}的自动刷新");
		}
	}


	/**
	 * 设置线程池大小
	 * @param poolSize 线程池大小
	 */
	public void setPoolSize(int poolSize){
		AccessTokenManager.poolsize = poolSize;
		LOGGER.info("[AccessTokenManager]设置线程池大小为:{"+poolSize+"}");
	}


	/**
	 * 获得默认token
	 * @return token
	 */
	public String getDefaultToekn(){
		LOGGER.info("[AccessTokenManager]获得默认appid:{"+defaultAppid+"}的accesstoken");
		return tokenMap.get(defaultAppid);
	}

	/**
	 * 依据appid获得token
	 * @param appid appid
	 * @return token
	 */
	public String getToekn(String appid){
		LOGGER.info("[AccessTokenManager]获得默认appid:{"+appid+"}的accesstoken");
		return tokenMap.get(appid);
	}

}
