package com.mkl.hengyimes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wf.jwtp.provider.Token;
import org.wf.jwtp.provider.TokenStore;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HengyimesApplicationTests {

    @Autowired
    TokenStore tokenStore;
    @Test
    public void contextLoads() {
        String[] str1=new String[1];
        str1[0]="s";
        String[] str2=new String[1];
        str2[0]="r";
        Token oo = tokenStore.createNewToken("oo", str1, str2);
        System.out.println(oo.getAccessToken());
    }

}
