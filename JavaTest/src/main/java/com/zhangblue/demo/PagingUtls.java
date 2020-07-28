package com.zhangblue.demo;

import java.util.Collections;
import java.util.List;

/**
 * 分页公共类
 *
 * @author di.zhang
 * @date 2020/6/11
 * @time 21:27
 **/
public class PagingUtls<T> {


  public List<T> paging(List<T> listAll, int pageNum, int pageSize) {
    int beginIndex = (pageNum - 1) * pageSize;
    int endIndex = listAll.size() >= pageNum * pageSize ? pageNum * pageSize : listAll.size();
    if (beginIndex > endIndex) {
      return Collections.emptyList();
    }else{
      return listAll.subList(beginIndex, endIndex);
    }
  }
}
