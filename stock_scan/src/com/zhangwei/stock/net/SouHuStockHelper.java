package com.zhangwei.stock.net;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.zhangwei.stock.androidconvert.Log;



public class SouHuStockHelper {
	private static SouHuStockHelper ins;
	private final String TAG = "SouHuStockHelper";

	//http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/601398.phtml?year=2007&jidu=2
	String souhu_stock_rank = "http://q.stock.sohu.com/cn/ph_m.shtml?type=sh_as&field=changerate&sort=up";
	
	
	private SouHuStockHelper(){
		
	}
	
	public static SouHuStockHelper getInstance(){
		if(ins==null){
			ins = new SouHuStockHelper();
		}
		
		return ins;
	}
	
	public boolean judgeNetwork(){
		boolean result = false;
		try{
			Document doc = null;
			doc = Jsoup.connect("http://www.baidu.com").timeout(30000).get();
			if(doc!=null){
				result = true;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 *  从新浪获取历史记录，从较近到较远
	 *  从begindate（包含） 到 enddate（包含）, begindate is after enddate 逆序
	 * */
	public ArrayList<RankItem> get_ranklist_from_souhu(){

		
		ArrayList<RankItem> result = new ArrayList<RankItem>();
		

		//do..
		try {
			Document doc = null;
			String connect_url = souhu_stock_rank;
			doc = Jsoup.connect(connect_url).timeout(30000).get();
			JsoupHelper jh = new JsoupHelper();
			
			//rank
			Element BIZ_MS_pllist = doc.body().getElementById("BIZ_MS_pllist");

			
			if(BIZ_MS_pllist!=null){
				//Log.e(TAG, FundHoldSharesTable.text());
				ArrayList<Element> trs = jh.dump(BIZ_MS_pllist, "tr");
				if(trs.size()>1){
					for(int index=1; index<trs.size() ; index++){
						Log.e(TAG, trs.get(index).text());
						
						ArrayList<Element> tds = jh.dump(trs.get(index), "td");
/*						if(tds!=null && tds.size()==7){
							RankItem hRecord = new RankItem();
							
							result.add(hRecord);
						}*/
						
					}
				}

			}

		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}

		
		return result;
		
	}

	

	
}
