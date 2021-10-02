package Weather;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

class WeatherResponse {
    protected static void parseTimelineJson(String rawResult) throws ParseException {

        if (rawResult==null || rawResult.isEmpty()) {
            System.out.printf("No raw data%n");
            return;
        }

        JSONObject timelineResponse = new JSONObject(rawResult);

        JSONArray values=timelineResponse.getJSONArray("DailyForecasts");

        for (int i = 0; i < values.length(); i++) {
            JSONObject dayValue = values.getJSONObject(i);
            JSONObject temperature = dayValue.getJSONObject("Temperature");
            double min = temperature.getJSONObject("Minimum").getDouble("Value");
            double minCelsius = (( 5 *(min - 32.0)) / 9.0);

            double max = temperature.getJSONObject("Maximum").getDouble("Value");
            double maxCelsius = (( 5 *(max - 32.0)) / 9.0);
            String desc = dayValue.getJSONObject("Day").getString("IconPhrase");

            // | В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE
            System.out.printf("| В городе Санкт-Петербург на дату %s ожидается %s, температура - %.1f - %.1f %n",
                    dayValue.getString("Date").substring(0, 10), desc, minCelsius,
                    maxCelsius);
        }
    }
}

public class Weather {

    public static void timelineRequestHttpClient() throws Exception {
        //set up the end point
        String apiEndPoint="https://dataservice.accuweather" +
                ".com/forecasts/v1/daily/5day/295212?apikey=LunS7GOmWeIA8pFAUFyCqIY37E6Neg5Z&language=RU";


        StringBuilder requestBuilder=new StringBuilder(apiEndPoint);

        URIBuilder builder = new URIBuilder(apiEndPoint);



        HttpGet get = new HttpGet(builder.build());

        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse response = httpclient.execute(get);

        String rawResult=null;
        try {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.printf("Bad response status code:%d%n", response.getStatusLine().getStatusCode());
                return;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                rawResult=EntityUtils.toString(entity, Charset.forName("utf-8"));
            }


        } finally {
            response.close();
        }

        WeatherResponse.parseTimelineJson(rawResult);
    }


    public static void main(String[] args)  throws Exception {
        Weather.timelineRequestHttpClient();
    }
}
