package kha.TestData;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public List<HashMap<String,String>> mapJsonData(String filepath) throws IOException {

        String jsonContent = FileUtils.readFileToString(new File(System.getProperty(filepath)), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
    }

}
//    @DataProvider
//    public Object[][] getData(){
////        HashMap<String,String>map=new HashMap<String,String>();
////        map.put("email","korey.subscribes10@gmail.com");
////        map.put("password","1LONGpassword");
////        map.put("product","ADIDAS ORIGINAL");
////
////        HashMap<String,String>map1=new HashMap<String,String>();
////        map1.put("email","korey.subscribes10@gmail.com");
////        map1.put("password","1LONGpassword");
////        map1.put("product","ZARA COAT 3");
////        return new Object[][]{{map},{map1}};
////        return new Object[][] {{"korey.subscribes10@gmail.com","1LONGpassword","ADIDAS ORIGINAL"},{"korey.subscribes10@gmail.com","1LONGpassword","ZARA COAT 3"}};
//    }

