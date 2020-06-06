package pl.konrad_wajs.order_items.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("default")
public class DataSourceConfig {

    //TODO - change and hide credentials in AWS

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://sql.miedwie.home" +
                ".pl:3306/00347185_item_order?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode" +
                "=false&serverTimezone=UTC\n");
        dataSourceBuilder.username("00347185_item_order");
        dataSourceBuilder.password("1234ItemOrder");
        return dataSourceBuilder.build();
    }
}