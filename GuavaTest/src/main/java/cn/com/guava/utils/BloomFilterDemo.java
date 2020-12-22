package cn.com.guava.utils;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import java.nio.charset.Charset;

/**
 * @author di.zhang
 * @date 2020/8/19
 * @time 16:12
 **/
public class BloomFilterDemo {

  public static void main(String[] args) {
    BloomFilter<String> bloomFilter = BloomFilter
        .create(Funnels.stringFunnel(Charset.forName("UTF-8")), 2000);




    bloomFilter.put("www.baidu.com");
    bloomFilter.put("www.163.com");
    System.out.println(bloomFilter.mightContain("www.126.com"));
    System.out.println(bloomFilter.mightContain("www.163.com"));
    System.out.println(bloomFilter.mightContain("www.baidu.com"));


  }

}
