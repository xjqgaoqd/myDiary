package com.socket.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**本类用来表示Socket网络编程案例的服务器端
 * 完成步骤分析:
 * 1.启动服务器
 * 2.接收客户端的连接请求
 * 3.接收客户端发来的数据
 * */
/**测试注意事项:先启动服务器端,再启动客户端*/
public class Server {
    public static void main(String[] args) throws Exception {
        //1.启动服务器,指定端口号为8888,等待客户端的连接
        /**注意:
         * 1.使用ServerSocket需要导包java.net.ServerSocket
         * 2.此操作会抛出异常
         * 3.指定的端口号范围是:0-655535,而0-1024是系统端口号,不能指定
         * */
        ServerSocket ss = new ServerSocket(8888);
        //2.接收客户端的连接请求,并建立数据通信通道
        Socket socket = ss.accept();
        //3.获取到读取流,接收并读取客户端发来的数据
        InputStream in = socket.getInputStream();
        //通过循环挨个读取显示读到的内容
        for(int i = 0;i < 5;i++) {
            //int b = in.read();//此方法读取的结果是把字符转成数字
            char c = (char) in.read();//为了直接显示读取到的字符,需要强制类型转换(大转小,int转char)
            System.out.print(c);//print()同行输出,注意细节哦
        }
        //5.给客户端发送数据
        OutputStream out = socket.getOutputStream();
        out.write("wwwwwww".getBytes());
        out.flush();

        //4.释放资源
        /**注意关流的顺序,后出现的先关闭*/
        in.close();
        ss.close();
    }
}
