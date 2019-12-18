package com.zhangblue.controller;


import com.zhangblue.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangdi
 */
@RestController
@RequestMapping("/kafka/producer/")
public class KafkaProduerController {

  @Autowired
  private KafkaProducerService kafkaProducerService;

  @RequestMapping(value = "/send", method = RequestMethod.POST)
  public String sendMeg(@RequestParam(name = "key") String key,
      @RequestParam(name = "msg") String msg) {
    String result = kafkaProducerService.sendMsg(key, msg);
    return result;
  }


}
