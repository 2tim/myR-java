package pricing;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Currency;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({App.class, PricingController.class, DataStore.class})
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DataStore repo;

    @Test
    public void putSomeItems() throws Exception {

        String uri = "/products/13860428";
        String json = "{\"value\":12.99,\"currency_code\":\"USD\"}";

        this.mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("success")))
                .andDo(document("PUT"));

        String uri2 = "/products/13860429";
        String json2 = "{\"value\":22.99,\"currency_code\":\"USD\"}";

        this.mockMvc.perform(put(uri2).contentType(MediaType.APPLICATION_JSON).content(json2))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("success")))
                .andDo(document("PUT2"));

    }

    @Test
    public void getSomeItems() throws Exception {
        Current_Price cp = new Current_Price();
        cp.setValue(BigDecimal.valueOf(12.99));
        Current_Price cp2 = new Current_Price();
        cp2.setValue(BigDecimal.valueOf(22.99));

        Currency curr = Currency.getInstance("USD");
        cp.setCurrency_code(curr);
        Price p = new Price(13860428L, cp);
        Price p2 = new Price(13860429L, cp2);
        Mockito.when(repo.findById(13860428L)).thenReturn(p);
        Mockito.when(repo.findById(13860429L)).thenReturn(p2);
        this.mockMvc.perform(get("/products/13860428")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("The Big Lebowski")))
                .andDo(document("GET"));
        this.mockMvc.perform(get("/products/13860429")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("SpongeBob SquarePants")))
                .andDo(document("GET2"));
    }
}
