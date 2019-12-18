<!-- Logback configuration. See http://logback.qos.ch/manual/index.html -->
<configuration scan="true" scanPeriod="10 seconds">
  <include resource="org/springframework/boot/logging/logback/base.xml"/>


  <!-- ch.qos.logback.core.ConsoleAppender 表示控制台输出 -->
  <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
    <!--
    日志输出格式：
  %d表示日期时间，
  %thread表示线程名，
  %-5level：级别从左显示5个字符宽度
  %logger{50} 表示logger名字最长50个字符，否则按照句点分割。
  %msg：日志消息，
  %n是换行符
    -->
    <layout class="ch.qos.logback.classic.PatternLayout">
      <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}][%thread][%-5level][%logger{36}] -%msg%n</pattern>
    </layout>
  </appender>

  <appender name="DEBUG_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>DEBUG</level>
    </filter>
    <File>/Users/zhangdi/work/workspace/github/JavaLocalTest/KafkaTest/log/debug.log</File>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>
        /Users/zhangdi/work/workspace/github/JavaLocalTest/KafkaTest/log/debug-%d{yyyyMMdd}.log.%i
      </fileNamePattern>
      <timeBasedFileNamingAndTriggeringPolicy
        class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
        <maxFileSize>200MB</maxFileSize>
      </timeBasedFileNamingAndTriggeringPolicy>
      <maxHistory>10</maxHistory>
    </rollingPolicy>
    <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
      <layout class="ch.qos.logback.classic.PatternLayout">
        <Pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}][%thread][%-5level][%logger{36}] -%msg%n
        </Pattern>
      </layout>
      <charset class="java.nio.charset.Charset">UTF-8</charset>
      <!--            <charset>UTF-8</charset>-->
    </encoder>
  </appender>

  <appender name="INFO_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
      <level>INFO</level>
    </filter>
    <File>/Users/zhangdi/work/workspace/github/JavaLocalTest/KafkaTest/log/info.log</File>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>
        /Users/zhangdi/work/workspace/github/JavaLocalTest/KafkaTest/log/info-%d{yyyyMMdd}.log.%i
      </fileNamePattern>
      <timeBasedFileNamingAndTriggeringPolicy
        class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
        <maxFileSize>200MB</maxFileSize>
      </timeBasedFileNamingAndTriggeringPolicy>
      <maxHistory>10</maxHistory>
    </rollingPolicy>
    <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
      <layout class="ch.qos.logback.classic.PatternLayout">
        <Pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}][%thread][%-5level][%logger{36}] -%msg%n
        </Pattern>
      </layout>
      <charset class="java.nio.charset.Charset">UTF-8</charset>
    </encoder>
  </appender>

  <appender name="ERROR_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
      <level>ERROR</level>
    </filter>
    <File>/Users/zhangdi/work/workspace/github/JavaLocalTest/KafkaTest/log/error.log</File>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>
        /Users/zhangdi/work/workspace/github/JavaLocalTest/KafkaTest/log/error-%d{yyyyMMdd}.log.%i
      </fileNamePattern>
      <timeBasedFileNamingAndTriggeringPolicy
        class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
        <maxFileSize>200MB</maxFileSize>
      </timeBasedFileNamingAndTriggeringPolicy>
      <maxHistory>20</maxHistory>
    </rollingPolicy>
    <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
      <layout class="ch.qos.logback.classic.PatternLayout">
        <Pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}][%thread][%-5level][%logger{36}] -%msg%n
        </Pattern>
      </layout>
      <charset class="java.nio.charset.Charset">UTF-8</charset>
    </encoder>
  </appender>

  <logger name="o.a.tomcat" value="ERROR_FILE"/>

  <root level="debug">
    <!--    <appender-ref ref="console"/>-->
    <appender-ref ref="DEBUG_FILE"/>
    <appender-ref ref="INFO_FILE"/>
    <appender-ref ref="ERROR_FILE"/>
  </root>

</configuration>