import com.sun.jndi.toolkit.url.Uri;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testGet {

    public static void main(String[] args) {
        String a=doGet();
        System.out.println(convertUnicodeToCh(a));

    }



    public  static  String doGet(){
        String strResult="";
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet("https://app.dewu.com/api/v1/app/deposit-interfaces/js/apply/product");
            request.setHeader("Connection","keep-alive");
            request.setHeader("Accept","*/*");
            request.addHeader("host","app.dewu.com");
            request.addHeader("token", "JLIjsdLjfsdII%3D%7CMTQxODg3MDczNA%3D%3D%7C07aaal32795abdeff41cc9633329932195");
            request.addHeader("uuid", "F1046A8A-2E87-437A-9E96-4852C1A96CAF");
            request.addHeader("shumeiid",   "202001281953074cddd9875b6dd9eb897e72eb9c86f7a701477c222738d83c");
            request.addHeader("timestamp", "1598002424660");
            request.addHeader("loginToken", "ff57d687|183840644|459d7bfcaade176d");
            request.addHeader("appId", "duapp");
            request.addHeader("X-Auth-Token", "DUApp/4.50.5 (com.siwuai.duapp; build:4.50.5.10; iOS 13.5.0) Alamofire/5.2.2\n" +
                    "X-Auth-Token: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJGMTA0NkE4QS0yRTg3LTQzN0EtOUU5Ni00ODUyQzFBOTZDQUYiLCJzdWIiOiJGMTA0NkE4QS0yRTg3LTQzN0EtOUU5Ni00ODUyQzFBOTZDQUYiLCJpYXQ" +
                    "iOjE1OTgwMDI0MjIsImV4cCI6MTYyOTUzODQyMiwidXVpZCI6IkYxMDQ2QThBLTJFODctNDM3QS05RTk2LTQ4NTJDMUE5NkNBRiIsInVzZXJJZCI6MTgzODQwNjQ0LCJ1c2VyTmFtZSI6Ilx1OGZmN1x" +
                    "1ODMyYlx1NGY3M1x1NWY5N1x1NGU1MEhrcSIsImlzR3Vlc3QiOmZhbHNlfQ.d8EX0GCmMCvezJTG3DooBPdD4iXqgkhtoJzmz12sS263MGS5qTLWJFrnTu8FiDNOqvFQ-fz692UrBnrZfWCbM3SdRyPn" +
                    "lunhrSY73fKdB01D5mZ7scqcD7biq8WYY1WvphQBTupR91hILAdfmreZhLZx4GKfbqv-qVKNON2jQsWGc3RFN4DIn0vkCkxTq8" +
                    "hFzH40nN038MFkuNkpV3PCiIrmOV2qSIsQWu0sw9Eoksq4CYT5sLJLSciK1wDRgeTAP4he_J9yF2z7CTq__9Z-vKn-VpHTH_TqZlQl02cHUjJhfDwKahd6jW_e2vLR-XZlS7DO5ker3UJepWBEK6LyCQ\n");
            request.addHeader("Host", "app.dewu.com");
            request.addHeader("User-Agent", "DUApp/4.50.5 (com.siwuai.duapp; build:4.50.5.10; iOS 13.5.0) Alamofire/5.2.2");
            request.addHeader("cookieToken", "d41d8cd9|183840644|1597931501|2d8b73c67337cffd");
            request.addHeader("emu", "0");
            request.addHeader("isProxy", "1");
            request.addHeader("isRoot", "0");
            request.addHeader("mode", "0");
            request.addHeader("platform", " iPhone");
            request.addHeader("v", " 4.50.5");
            request.addHeader("loginToken ", "ff57d687|183840644|459d7bfcaade176d");
            request.addHeader("newSign ", "433ad0c1b392bf3978e698f74e1ea387");
            request.addHeader("Cookie", "duToken=d41d8cd9|183840644|1597931501|2d8b73c67337cffd");


            HttpResponse response = client.execute(request);
            strResult = EntityUtils.toString(response.getEntity());
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
             /**读取服务器返回过来的json字符串数据**/



            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return strResult;
    }
    /**
     * 将unicode字符串转为正常字符串
     *
     * @param str unicode字符串（比如"\u67e5\u8be2\u6210\u529f"）
     * @return 转换后的字符串（比如"查询成功"）
     */
    private static String convertUnicodeToCh(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\w{4}))");
        Matcher matcher = pattern.matcher(str);

        // 迭代，将str中的所有unicode转换为正常字符
        while (matcher.find()) {
            String unicodeFull = matcher.group(1); // 匹配出的每个字的unicode，比如\u67e5
            String unicodeNum = matcher.group(2); // 匹配出每个字的数字，比如\u67e5，会匹配出67e5

            // 将匹配出的数字按照16进制转换为10进制，转换为char类型，就是对应的正常字符了
            char singleChar = (char) Integer.parseInt(unicodeNum, 16);

            // 替换原始字符串中的unicode码
            str = str.replace(unicodeFull, singleChar + "");
        }
        return str;
    }
}


