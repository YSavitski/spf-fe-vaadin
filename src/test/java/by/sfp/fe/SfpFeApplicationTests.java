package by.sfp.fe;

import by.sfp.fe.domain.DomainCategory;
import by.sfp.fe.util.RestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SfpFeApplicationTests {
    @Autowired
    private RestClient client;

    @Test
    public void getCategories() {
        List<DomainCategory> domainCategories = client.getDomainCategories();
        assertNotNull(domainCategories);
    }

}