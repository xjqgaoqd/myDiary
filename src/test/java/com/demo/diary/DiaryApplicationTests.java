package com.demo.diary;

import com.demo.diary.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@SpringBootTest
class DiaryApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Test
//    public static void main(String[] args) {
//        String client_id = "ztgl-app-2868ef5b-69bb-443b-baeb-546346afc6e0";
//        String client_secret = "ouO2xNe7Cq2uEgehk7piVXwKCAK60OYV3goWrrfSd9FA5lVGlUtPbbIjGgWaRGRhdMauKFq4z4wAdca6vJihjJHYyqTDFfw/r97FLtvTRUI6w23tJwiwr74z1kuzmklY/";
//        String auth = client_id + ":"+client_secret;
//        byte[] encodedAuth = org.apache.commons.codec.binary.Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
//        String authorization= "Basic " + new String(encodedAuth);
//        System.out.println(authorization);
//    }


    @Test
    public void test(){

    }

    Integer sum = 900;
    List<User> users = new ArrayList<>();

    @Test
    public void testSum(){
        Integer pageIndex = 1;
        Integer pageSize = 500;
        boolean isOk = false;

        int sumCount = sum;

        while (!isOk){

        }
    }

    List<User> getUsers(Integer pageIndex,Integer pageSize){
        int i = pageIndex * pageSize;
        List<User> b = null;
        if (pageIndex ==1){
            b = users.subList(0,i-1);
        }else {
            b = users.subList((pageIndex-1)*pageSize,i);
        }
        return b;
    }
}
