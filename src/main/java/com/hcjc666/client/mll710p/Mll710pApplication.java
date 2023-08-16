package com.hcjc666.client.mll710p;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import com.alibaba.fastjson2.JSON;
import com.hcjc666.client.mll710p.config.ClientConfig;
import com.hcjc666.client.mll710p.config.ServerConfig;
import com.hcjc666.client.mll710p.entity.EquipmentInfo;
import com.hcjc666.client.mll710p.service.EquipmentInfoService;
import com.hcjc666.client.mll710p.util.LogUtils;
import com.hcjc666.client.mll710p.util.StringUtils;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@SpringBootApplication
@MapperScan("com.hcjc666.client.mll710p.mapper")
@EnableAsync
public class Mll710pApplication implements CommandLineRunner {

	@Autowired
	private ClientConfig clientConfig;
	@Autowired
	private ServerConfig serverConfig;
	@Autowired
	private EquipmentInfoService equipmentInfoService;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Mll710pApplication.class);
		app.run(args);
		System.out.println("Hello World!");
	}

	@Async
	@Override
	public void run(String... args) throws Exception {

		// 定时发送数据查询请求
		while (true) {
			LogUtils.getPlatformLogger().info("发送查询数据请求!");
			postQuery();
			Thread.sleep(clientConfig.getIntervalSeconds() * 1000);
			LogUtils.getPlatformLogger().info("发送查询数据请求!完成");
		}
	}

	/**
	 * 发送查询请求
	 * 先查询所有当前设备，然后统一进行生成命令。统一发送到tcp服务器处理
	 */
	private void postQuery() {
		EquipmentInfo info = new EquipmentInfo();
		info.setEquipmentType(clientConfig.getName());
		List<EquipmentInfo> list = equipmentInfoService.getList(info);
		if (list.size() == 0) {
			LogUtils.getPlatformLogger().info("未查询到设备列表,equipmentType:" + clientConfig.getName());
			return;
		}

		String jsonstr = "";
		try {
			Date now = new Date();
			List<Map<String,Object>> cmds = new ArrayList<>();
			for (EquipmentInfo temp : list) {
				Map<String,Object> jb = new HashMap();
				jb.put("cmd", generateCmd(temp.getModbusAddr()));
				jb.put("imei", temp.getDtuImei());
				cmds.add(jb);
			}
			InetAddress addr = InetAddress.getLocalHost();
			Map<String,Object> map = new HashMap<>();
			map.put("cmds", cmds);
			map.put("time", now.getTime());
			map.put("client", clientConfig.getName());
			map.put("ip", addr.getHostAddress());
			map.put("port", serverConfig.getPort());
			jsonstr = JSON.toJSONString(map);

			OkHttpClient okHttpClient = new OkHttpClient();
			MediaType JSON = MediaType.parse("application/json; charset=utf-8");
			RequestBody body = RequestBody.create(JSON, jsonstr);
			Request request = new Request.Builder()
					.url(clientConfig.getQueryUrl())
					.post(body)
					.build();
			Call call = okHttpClient.newCall(request);

			Response response = call.execute();
			LogUtils.getPlatformLogger().info("发送查询请求，url：" + clientConfig.getQueryUrl() + "，参数：" + jsonstr);
			LogUtils.getPlatformLogger().info(response.body().string());
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.getExceptionLogger().error("数据发送到指定url服务去解析保存,失败! url：" + clientConfig.getQueryUrl()
					+ ",jsonstr:" + jsonstr + "," + e.getMessage());
		}
	}

	/**
	 * 根据地址位，生成查询命令
	 * 
	 * @param modbusAddr
	 * @return
	 */
	private String generateCmd(String modbusAddr) {
		Integer x = Integer.valueOf(modbusAddr);
		String cmd = x.toHexString(x) + " 03 00 10 00 0E";
		String crc = StringUtils.getCrcModBus(cmd.replaceAll(" ", ""));
		crc = crc.replaceAll("(.{2})", "$1 ");
		String[] crcarr = crc.split(" ");
		return cmd + " " + crcarr[1] + " " + crcarr[0];
	}
}
