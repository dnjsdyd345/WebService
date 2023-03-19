package com.spring.blogservice.service;

import com.spring.blogservice.entity.SearchBlog;
import com.spring.blogservice.entity.SearchBlogReq;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainService {

    @Value("${RestApi.kakao.host}")
    String host;
    @Value("${RestApi.kakao.path}")
    String path;
    @Value("${RestApi.kakao.key}")
    String key;


    public String test2(String query){
        Map<String, Object> params = new HashMap<>();
        params.put("query", query);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", key);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        String url = host + path + "?query="+query ;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, params);
        //url 메소드 파라미터 셋팅
//        Map<String, Object> params = new HashMap<>();
//        params.put("query", query);

        //RestTemplate 객체 생성
//        RestTemplate template = new RestTemplate();
//        String response = "";

//        try {
//        String url = host + path + "?query=" + query;
//        response = template.getForObject(url, String.class, params);

//        } catch (HttpStatusCodeException e) {
//            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {

//				HttpStatusCodeException
//				HttpClientErrorException
//				HttpServerErrorException

//            }

//        }
        return response.toString();
    }
    public ResponseEntity<List> test(String query){
/*

String url = host + path;

        HttpWebRequest request = (HttpWebRequest) WebRequest.Create(url); // 해당 URL로 네트웍을 만든다
        request.Headers.Add("Authorization", "KakaoAK " + rest_api_key); // 헤더에 옵션값을 추가한다.
        request.ContentType = "application/x-www-form-urlencoded";// 콘텐츠타입을 명시한다
        request.Method = "GET"; // get 으로 보낼지 post로 보낼지 명시한다.

        String responseText = "";
        using(WebResponse response = request.GetResponse()) // 보낸데이터를 기반으로 받는다
        {
            Stream stream = response.GetResponseStream(); // 받은 데이터를 스트림으로 쓴다
            using(StreamReader sr = new StreamReader(stream)) // 스트림을 읽기 위해 리더를 오픈한다.
            {
                responseText = sr.ReadToEnd(); // 스트림의 내용을 읽어서 문자열로 반환해준다.
            }

            Console.WriteLine(responseText); // 내용을 로그로 출력한다.
        }

// ---

            URI uri = UriComponentsBuilder
                    .fromUriString("http:localhost:8081")
                    .path("/")
                    .encode()
                    .build()
                    .expand("100","ila") //순차적으로 파라미터넣기
                    .toUri();

            System.out.println(uri);

            // http body -> object -> object mapper -> json -> rest template -> http body json
            // http body를 만들건데 object만 보낼거야 object mapper가 json을만들어서 rest template에 보내서 http body에 json으로 넣어줄것이다.

            SearchBlogReq req = new SearchBlogReq();
            req.setQuery(query);

            RequestEntity<SearchBlogReq> requestEntity = RequestEntity
                    .get(host)
                    .header("Authorization", key)

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<SearchBlog> response = restTemplate.exchange(requestEntity, SearchBlog.class);
            return response.getBody();
*/
          /*  String url = host + path;
            //get parameter 담아주기
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("Authorization", key)
                    .queryParam("query", query);

            builder.encode();

            HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            httpRequestFactory.setConnectTimeout(30000); // 연결시간 초과
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("Authorization", key);
            params.add("query", query);

            //Rest template setting
            RestTemplate restTpl = new RestTemplate(httpRequestFactory);
            HttpHeaders headers  = new HttpHeaders(); // 담아줄 header
            headers.add("Authorization", key);
            headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            Charset utf8 = Charset.forName("UTF-8");
            MediaType mediaType = new MediaType("application", "json", utf8);
            headers.setContentType(mediaType);
            HttpEntity entity = new HttpEntity<>(params,headers); // http entity에 header 담아줌
            restTpl.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));
            ResponseEntity<String>  responseEntity = restTpl.exchange(url, HttpMethod.GET, entity, String.class);
            List result = new ArrayList();
*/

            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = host + path;
            ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);

            List result = new ArrayList();
            return ResponseEntity.ok(result);
        }


}
