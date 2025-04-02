package cn.webwss.talent_pool_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.webwss.talent_pool_backend.mapper")
public class TalentPoolBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalentPoolBackendApplication.class, args);
    }

}
