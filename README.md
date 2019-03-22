# Spring Boot 中使用 JavaMailSender 发送邮件

关键点：
1. application.properties 中 spring.mail.password 需要16位授权码进行验证（以 qq 邮箱为例）@see https://service.mail.qq.com/cgi-bin/help?subtype=1&&id=28&&no=1001256；
2. 发送带有附件或是使用邮件模板的邮件，需要 MimeMessage 来设置复杂一些的邮件内容
