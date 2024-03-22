# Getting Started
Important part is the configure logback.xml as following
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %replace(%msg){'(?&lt;=(firstName=|\"firstName\":\")|(lastName=|\"lastName\":\")|(age=|\"age\":)|(creditCardNumber=|\"creditCardNumber\":)).+?(?=\'|\"|,|\))', '****'} %n</pattern>
        </encoder>
    </appender>

    <appender name="RollingFile" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>log/boot-example-log-masking.log</file>

        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %replace(%msg){'(?&lt;=(firstName=|\"firstName\":\")|(lastName=|\"lastName\":\")|(age=|\"age\":)|(creditCardNumber=|\"creditCardNumber\":)).+?(?=\'|\"|,|\))', '****'} %n</pattern>
        </encoder>

        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>boot-example-log-masking-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxFileSize>1MB</maxFileSize>
            <maxHistory>30</maxHistory>
            <totalSizeCap>10MB</totalSizeCap>
            <cleanHistoryOnStart>true</cleanHistoryOnStart>
        </rollingPolicy>
    </appender>

    <root level="INFO">
        <appender-ref ref="Console" />
        <appender-ref ref="RollingFile" />
    </root>
</configuration>
```

From the above logback.xml configuration, the following part replace the certain log will be replaced with ****
```
<pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %replace(%msg){'(?&lt;=(firstName=|\"firstName\":\")|(lastName=|\"lastName\":\")|(age=|\"age\":)|(creditCardNumber=|\"creditCardNumber\":)).+?(?=\'|\"|,|\))', '****'} %n</pattern>
```
Above regex pattern will replace the part of the log where
* between `firstName= or "firstName":"` and till meet `' or " or , or )`
* between `lastName= or "lastName":"` and till meet `' or " or , or )`
* between `age= or "age":` and till meet `' or " or , or )`
* between `creditCardNumber= or "creditCardNumber":` and till meet `' or " or , or )`

When you log the Person object, it will be logged like this
```
Person(firstName=****, lastName=****, creditCardNumber=****, address=123 ABC Street)
```