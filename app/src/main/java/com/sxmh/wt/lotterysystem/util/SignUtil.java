package com.sxmh.wt.lotterysystem.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author syl 2018-03-02 签名工具类
 */
public class SignUtil {

	// 测试签名方法
	public static void main(String[] args) {
		JSONObject data = new JSONObject();
		JSONObject orderInfo = new JSONObject();
		orderInfo.put("gameId", "195");
		orderInfo.put("gameAlias", "kl8");

		JSONArray ticketList = new JSONArray();
		JSONObject ticketMap = new JSONObject();
		ticketMap.put("ticket", "04 05 06 07");
		ticketMap.put("betMode", "01");
		ticketMap.put("totalMoney", "200");
		ticketList.add(ticketMap);
		orderInfo.put("ticketList", ticketList);

		JSONArray drawList = new JSONArray();
		JSONObject drawMap = new JSONObject();
		drawMap.put("drawId", "3595");
		drawMap.put("drawNumber", "2018099");
		drawList.add(drawMap);
		orderInfo.put("drawList", drawList);

		orderInfo.put("terminalId", "22");
		orderInfo.put("terminal", "100202");
		orderInfo.put("multiDraw", "1");
		orderInfo.put("betDouble", "1");
		orderInfo.put("noteNumber", "1");
		orderInfo.put("totalMoney", "2");
		orderInfo.put("betMode", "01");
		orderInfo.put("drawId", "7803");
		orderInfo.put("drawNumber", "908552");
		orderInfo.put("gameIdAdd", "4");
		orderInfo.put("frisbeeStatus", "00");
		data.put("orderInfo", orderInfo);

		sign(data);
	}

	// 拼接json键值对 md5加密 签名方法
	@SuppressWarnings("rawtypes")
	public static String sign(JSONObject data) {
		System.out.println(data+"json");
		JSONObject orderInfo = data.getJSONObject("orderInfo");
		Map<String, Object> packageParams = JSON.parseObject(orderInfo.toJSONString());

		//排序
		Map<String, Object> map = new TreeMap<String, Object>();
		for (String key : packageParams.keySet()) {
			map.put(key, packageParams.get(key));
		}
		
		StringBuffer sb = new StringBuffer();
		Set es = map.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			
			String v = "";
			if (k.equals("ticketList") || k.equals("drawList")) {
				JSONArray list = (JSONArray) entry.getValue();
				v = list.toJSONString();
			}else {
				v = String.valueOf(entry.getValue());
			}

			if (null != v && !"".equals(v) && !"sign".equals(k)&& !"key".equals(k)) {				
				sb.append(k + "=" + v + "&");
			}
		}

		System.out.println("参与验参:" + sb.toString() );
		String sign = MD5.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        System.out.println("sign签名: "+sign);
		
		return sign;
	}

}
