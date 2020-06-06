package pl.konrad_wajs.order_items.base;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.konrad_wajs.order_items.Application;
import pl.konrad_wajs.order_items.config.H2JpaConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@ActiveProfiles("test")
public abstract class AbstractIntegrationTestBase {
}
