package com.socket.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**本类用来表示Socket网络编程案例的客户端
 * 完成步骤分析:
 * 1.指定要连接的服务器
 * 2.给服务器发送hello
 * */
public class Client {
    public static void main(String[] args) throws Exception {
        //1.指定要连接的服务器,需要同时指定服务器的IP & Port
        /**注意:
         * 1.使用Socket需要导包java.net.Socket
         * 2.此操作会抛出异常
         * 3.如果使用的是本机的IP,地址是固定值,用来测试时使用127.0.0.1
         * */
        Socket socket = new Socket("127.0.0.1",8888);
        //2.给服务器端发送hello
        OutputStream out = socket.getOutputStream();
        //把要输出的数据hello字符串转变成byte[]的形式进行输出
        out.write("2022".getBytes());
        out.flush();

        //4.读取从服务器端返回的数据
        InputStream in = socket.getInputStream();
        for (int i = 0; i < 5; i++) {
            char c = (char) in.read();//为了显示字符而不是数字,强制类型转换成char
            System.out.print(c);//不换行展示获取到的数据
        }

        //3.释放资源
        //out.close();
        socket.close();

    }
}

