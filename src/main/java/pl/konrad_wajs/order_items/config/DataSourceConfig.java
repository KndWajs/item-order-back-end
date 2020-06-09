package pl.konrad_wajs.order_items.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.konrad_wajs.order_items.aws.SSMClient;

import javax.sql.DataSource;

@Configuration
@Profile("default")
public class DataSourceConfig {

    //TODO - change and hide credentials in AWS

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(SSMClient.getParameter("item-order.datasource.url", false));
        dataSourceBuilder.username(SSMClient.getParameter("item-order.datasource.username", false));
        dataSourceBuilder.password(SSMClient.getParameter("item-order.datasource.password", false));
        return dataSourceBuilder.build();
    }
}