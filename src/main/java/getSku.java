

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getSku   implements Runnable  {
    static int  num;
    public  static String skuMessage="";
    public void run() {
        while(1==1) {
            try {
                getSku.httpGet("https://mdskip.taobao.com/mobile/queryH5Detail.htm?decision=sku&itemId=20739895092&areaId=440000", "GBK", 1);
                Thread.sleep(1000*60);
            } catch (HttpException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {//mao  20739895092   mate40  634411655593
        //pcCode = {11: '北京市', 12: '天津市', 13: '河北省', 14: '山西省', 15: '内蒙古自治区', 21: '辽宁省', 22: '吉林省',
        //          23: '黑龙江省', 31: '上海市', 32: '江苏省', 33: '浙江省', 34: '安徽省', 35: '福建省', 36: '江西省',
        //          37: '山东省', 41: '河南省', 42: '湖北省', 43: '湖南省', 44: '广东省', 45: '广西壮族自治区', 46: '海南省',
        //          50: '重庆市', 51: '四川省', 52: '贵州省', 53: '云南省', 54: '西藏自治区', 61: '陕西省', 62: '甘肃省',
        //          63: '青海省', 64: '宁夏回族自治区', 65: '新疆维吾尔自治区', 71: '台湾省', 81: '香港特别行政区', 82: '澳门特别行政区'}
        getSku getSku=new getSku();
        Thread mThread1=new Thread(getSku,"线程1");
        mThread1.start();


    }

public static String httpGet(String url, String charset,int method)
            throws HttpException, IOException  {
        String json = null;
        HttpGet httpGet = new HttpGet();

// 设置参数
        try {
            httpGet.setURI(new URI(url));
        } catch (URISyntaxException e) {
            throw new HttpException("请求url格式错误。"+e.getMessage());
        }
// 发送请求
    HttpClient client=new DefaultHttpClient();
    HttpResponse httpResponse = client.execute(httpGet);
// 获取返回的数据
        HttpEntity entity = httpResponse.getEntity();
        byte[] body = EntityUtils.toByteArray(entity);
        StatusLine sL = httpResponse.getStatusLine();
        int statusCode = sL.getStatusCode();
        if (statusCode == 200) {
            json = new String(body, charset);
            entity.consumeContent();
        } else {
            throw new HttpException("statusCode="+statusCode);
        }
if(method==1) {
    try {
        //title
        JSONObject jsonArray = new JSONObject(json);
        JSONObject item = new JSONObject(jsonArray.getString("item") + "");
        String title=item.getString("title");
        System.out.println(title);
        //sku
        JSONObject jsonArray2 = new JSONObject(jsonArray.get("skuCore") + "");
//        System.out.println(jsonArray2);
        JSONObject jsonArray3 = new JSONObject(jsonArray2.get("sku2info") + "");
//        System.out.println(jsonArray3);
        JSONObject jsonArray4 = new JSONObject(jsonArray3.get("0") + "");
//        System.out.println(jsonArray4);
        int newNum=Integer.parseInt(jsonArray4.get("quantity")+"");
        System.out.println("当前库存为 " + newNum);
        skuMessage=title+" 当前库存为:"+newNum;
        if(num!=newNum){
            num=newNum;
//        getSku.httpGet("http://wxpusher.zjiecode.com/api/send/message/?appToken=AT_Q45yzpNW3dKPNaFF0SLXHZCfMjMcPFrJ&content="+title.trim()+"库存为"+jsonArray4.get("quantity")+"&uid=UID_yV8nb3gdc7I6eYSBRWY0IQP3bcgk","UTF-8",2);
        String message=
                " { \"appToken\":\"AT_Q45yzpNW3dKPNaFF0SLXHZCfMjMcPFrJ\"," +
                "  \"content\":\" "+title+"库存为"+num+"\"," +
                "  \"summary\":\"库存提醒 "+title+"库存现在为"+num+" \"," +
                "  \"topicIds\":[ \n" +
                "      1205\n" +
                "  ]," +
                "  \"contentType\":2, " +
                "  \"uids\":[" +
                "      \"UID_yV8nb3gdc7I6eYSBRWY0IQP3bcgk\"" +
                "  ]}" ;
        getSku.post("http://wxpusher.zjiecode.com/api/send/message",message);
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }
}

        return json;
    }
    public static String post(String url,String data) {
//        try {
//            URL url = new URL(strURL);// 创建连接
//            HttpURLConnection connection = (HttpURLConnection)
//                    url.openConnection();
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setUseCaches(false);
//            connection.setInstanceFollowRedirects(true);
//            connection.setRequestMethod("POST");// 设置请求方式
//            connection.setRequestProperty("Accept","application/json");// 设置接收数据的格式
//            connection.setRequestProperty("Content-Type","application/json");// 设置发送数据的格式
//            connection.connect(); OutputStreamWriter out = new OutputStreamWriter( connection.getOutputStream(),"UTF-8");// utf-8编码
//            out.append(params); out.flush(); out.close(); // 读取响应
//            int length = (int) connection.getContentLength();// 获取长度
//            InputStream is = connection.getInputStream();
//            if (length != -1){
//                byte[] data = new byte[length];
//                byte[] temp = new byte[512];
//                int readLen = 0; int destPos = 0;
//                while ((readLen = is.read(temp)) > 0){
//                    System.arraycopy(temp, 0, data, destPos, readLen);
//                    destPos += readLen;
//                }
//                String result = new String(data, "UTF-8");
//                System.out.println(result);
//                return result;
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return "error";
        String response = null;

        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpPost httppost = new HttpPost(url);
                StringEntity stringentity = new StringEntity(data,
                        ContentType.create("application/json", "UTF-8"));
                httppost.setEntity(stringentity);
                httpresponse = httpclient.execute(httppost);
                response = EntityUtils
                        .toString(httpresponse.getEntity());

                System.out.println("response: " + response);
            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    public  String getSkuMessage(){
        return skuMessage;
    }

}
