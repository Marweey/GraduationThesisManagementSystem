package cn.lynu.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.DigestUtils;

public class Utils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String md5(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    //用来对下载文件名进行编码
    public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
        String agent = request.getHeader("User-Agent"); //获取浏览器
        if (agent.contains("Firefox")) {
            Base64.Encoder base64Encoder = Base64.getEncoder();
            filename = "=?utf-8?B?"
                    + base64Encoder.encodeToString(filename.getBytes("utf-8"))
                    + "?=";
        }else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

}
