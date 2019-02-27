/*
 Author:Roby de Visser
*/

package be.ordina.coldchain.Service;

import be.ordina.coldchain.controller.AccountController;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.StrictMath.min;
import static java.lang.StrictMath.round;

public class BlockchainDataService {

    public void sendToApi(MultiValueMap<String, String> requestBody, String url) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("PRIVATE-TOKEN", "xyz");
        headers.add("Accept", MediaType.ALL_VALUE);
        JSONObject jsonObject = null;
        try {
            HttpEntity formEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    url,
                    formEntity, String.class);

            if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
                try {
                    jsonObject = new JSONObject(responseEntity.getBody());
                    System.out.println(jsonObject);

                } catch (JSONException e) {
                    throw new RuntimeException("JSONException occurred");
                }
            }
        } catch (final HttpClientErrorException httpClientErrorException) {
            System.out.println(httpClientErrorException);

        } catch (HttpServerErrorException httpServerErrorException) {
            System.out.println(httpServerErrorException);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void dataGen() {
        AccountController accountController = new AccountController();

        List<String> DevEUI = new ArrayList<>();
        List<String> plaatsen = new ArrayList<>();


        plaatsen.add("FF0109CE");
        plaatsen.add("AF4687FE");
        plaatsen.add("DE9976FE");

        DevEUI.add("0E654546546VB");
        DevEUI.add("2A654546546VB");
        DevEUI.add("3B654546546VB");
        DevEUI.add("4C654546546VB");
        DevEUI.add("1D654546546VB");
        DevEUI.add("5F654546546VB");

        List<Date> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        Date d = new Date(2019 - 1900, 01, 21, 10, 15, 59);
        dates.add(d);
        d = new Date(2016 - 1900, 12, 20, 11, 44, 59);
        dates.add(d);
        d = new Date(2018 - 1900, 11, 1, 9, 17, 59);
        dates.add(d);
        d = new Date(2018 - 1900, 10, 10, 10, 10, 10);
        dates.add(d);
        d = new Date(2018 - 1900, 07, 07, 07, 07, 07);
        dates.add(d);
        d = new Date(2019 - 1900, 01, 01, 01, 01, 01);
        dates.add(d);

        Random rand = new Random();

        int plaats = 0;

        String sender = "";
        int starttemp = 0;
        int mintemp = 0;
        int maxtemp = 0;
        String transporter = "f124c6f0-f963-4aec-b1a1-60714d54a3c6";
        String verantwoordelijke = "";
        String receiver = "";
        String description = "";
        String reason = "";

        for (int i = 0; i < 6; i++) {
            boolean accept = true;

            switch (i) {
                case 0:
                    sender = "7a820641-afe0-400b-bcf9-43535b1747f4";
                    starttemp = 4;
                    mintemp = 1;
                    maxtemp = 7;
                    receiver = "a77b1048-e343-4658-8aef-5c1ca5938fa0";
                    description = "Vlees Nr: P6249";
                    break;
                case 1:
                    sender = "7a820641-afe0-400b-bcf9-43535b1747f4";
                    starttemp = 4;
                    mintemp = 1;
                    maxtemp = 7;
                    receiver = "a77b1048-e343-4658-8aef-5c1ca5938fa0";
                    description = "Vlees Nr: P16578";
                    break;
                case 2:
                    sender = "7a820641-afe0-400b-bcf9-43535b1747f4";
                    starttemp = 3;
                    mintemp = 1;
                    maxtemp = 7;
                    receiver = "7385d66c-b7e2-4fb9-9f74-e7fcf757c887";
                    description = "Vlees Nr: P99887";
                    break;
                case 3:
                    sender = "7a820641-afe0-400b-bcf9-43535b1747f4";
                    starttemp = 3;
                    mintemp = 1;
                    maxtemp = 7;
                    receiver = "7385d66c-b7e2-4fb9-9f74-e7fcf757c887";
                    description = "Vlees Nr: P652147";
                    break;
                case 4:
                    sender = "f3257742-8658-4f26-89c5-4ba95f7f8301";
                    starttemp = -20;
                    mintemp = -40;
                    maxtemp = -18;
                    receiver = "7385d66c-b7e2-4fb9-9f74-e7fcf757c887";
                    description = "Groenten Nr: P78785";
                    break;
                case 5:
                    sender = "f3257742-8658-4f26-89c5-4ba95f7f8301";
                    starttemp = -20;
                    mintemp = -40;
                    maxtemp = -18;
                    receiver = "7385d66c-b7e2-4fb9-9f74-e7fcf757c887";
                    description = "Groenten Nr: P656547";
                    break;
            }

            MultiValueMap<String, String> contractBody = new LinkedMultiValueMap<>();
            contractBody.add("DevEUI", DevEUI.get(i));
            contractBody.add("sender", sender);
            contractBody.add("receiver", receiver);
            contractBody.add("minTemperature", Integer.toString(mintemp));
            contractBody.add("maxTemperature", Integer.toString(maxtemp));
            contractBody.add("transporter", transporter);
            contractBody.add("timestamp", sdf.format(new Date()));
            contractBody.add("description", description);

            sendToApi(contractBody, "http://localhost:5000/lorainput/addTransaction");

            int ran = rand.nextInt(40 - 30) + 30;
            Calendar c = Calendar.getInstance();
            c.setTime(dates.get(i));
            for (int x = 0; x < ran; x++) {
                int ranom = rand.nextInt(5);
                switch (i) {
                    case 4:
                        starttemp += 1;
                        break;
                    default:
                        if (ranom == 0 && !(starttemp>=maxtemp) && !(starttemp <= mintemp)) {
                            starttemp = starttemp + (rand.nextInt(3) - 1);
                        }
                        break;
                }


                if (starttemp > maxtemp || starttemp < mintemp) {
                    accept = false;
                    reason = "Temperature reached " + starttemp;
                }
                if (x == round(ran / 3)) {
                    plaats = 1;
                    MultiValueMap<String, String> ResponsibilityChange = new LinkedMultiValueMap<>();
                    ResponsibilityChange.add("responsible", "d6ff1469-2679-46e2-8c40-93d20044e2cb");
                    ResponsibilityChange.add("DevEUI", DevEUI.get(i));
                    ResponsibilityChange.add("timestamp", sdf.format(new Date()));
                    sendToApi(ResponsibilityChange, "http://localhost:5000/lorainput/changeResponsibility");
                } else if (x == round(ran / 3) * 2) {
                    plaats = 2;
                    MultiValueMap<String, String> ResponsibilityChange = new LinkedMultiValueMap<>();
                    ResponsibilityChange.add("responsible", "62421493-28fd-4546-afbc-9e15dc246742");
                    ResponsibilityChange.add("DevEUI", DevEUI.get(i));
                    ResponsibilityChange.add("timestamp", sdf.format(new Date()));
                    sendToApi(ResponsibilityChange, "http://localhost:5000/lorainput/changeResponsibility");
                }

                Date tempDate = c.getTime();


                MultiValueMap<String, String> readingBody = new LinkedMultiValueMap<>();
                readingBody.add("DevEUI", DevEUI.get(i));
                readingBody.add("container", "temperatuur");
                readingBody.add("value", Integer.toString(starttemp));
                readingBody.add("timestamp", sdf.format(new Date()));
                readingBody.add("Lrcid", plaatsen.get(plaats));
                readingBody.add("time", sdf.format(tempDate));

                c.add(Calendar.MINUTE, 15);
                sendToApi(readingBody, "http://localhost:5000/lorainput/sensordata");
            }

            if (i != 5) {
                if (accept) {
                    MultiValueMap<String, String> acceptContract = new LinkedMultiValueMap<>();
                    acceptContract.add("DevEUI", DevEUI.get(i));
                    acceptContract.add("timestamp", sdf.format(new Date()));
                    sendToApi(acceptContract, "http://localhost:5000/lorainput/acceptContract");

                } else {
                    MultiValueMap<String, String> denieContract = new LinkedMultiValueMap<>();
                    denieContract.add("DevEUI", DevEUI.get(i));
                    denieContract.add("timestamp", sdf.format(new Date()));
                    denieContract.add("reason", reason);
                    sendToApi(denieContract, "http://localhost:5000/lorainput/DenyContract");
                }
            }
        }
    }
}
