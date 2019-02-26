/*
 Author:Roby de Visser
*/

package be.ordina.coldchain.controller;

import be.ordina.coldchain.Service.BlockchainDataService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = {"http://localhost:4200", "http://chainreact-af.s3-website.eu-west-3.amazonaws.com/"})
@RestController
@RequestMapping(value = "/lorainput")
public class LoraInputController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");

    BlockchainDataService blockchainDataService = new BlockchainDataService();

    @RequestMapping(value = "/GenerateJson", method = RequestMethod.GET)
    public String createJson() {
        blockchainDataService.dataGen();
        return "pushing...";
    }

    @RequestMapping(value = "/sensordata", method = RequestMethod.POST)
    public MultiValueMap<String, String> pushToInputToAPI(
            @RequestParam(value = "DevEUI") String DevEUI,
            @RequestParam(value = "container") String container,
            @RequestParam(value = "value") String value,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "Lrcid") String Lrcid) {

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("$class", "org.ordina.coldchain.perishable.NewReading");
        requestBody.add("DevEUI", DevEUI);
        requestBody.add("container", container);
        requestBody.add("value", value);
        requestBody.add("timestamp", sdf.format(new Date()));
        requestBody.add("Lrcid", Lrcid);
        requestBody.add("time", timestamp);

        blockchainDataService.sendToApi(requestBody, "http://hyperledger.vanhool.net:3000/api/org.ordina.coldchain.perishable.NewReading");

        return requestBody;
    }

    @RequestMapping(value = "addTransaction", method = RequestMethod.POST)
    public MultiValueMap<String, String> pushToTransactionAPI(
            @RequestParam(value = "DevEUI") String DevEUI,
            @RequestParam(value = "sender") String sender,
            @RequestParam(value = "receiver") String receiver,
            @RequestParam(value = "minTemperature") String minTemperature,
            @RequestParam(value = "maxTemperature") String maxTemperature,
            @RequestParam(value = "transporter") String transporter,
            @RequestParam(value = "description") String description) {

        MultiValueMap<String, String> contractBody = new LinkedMultiValueMap<>();
        contractBody.add("$class", "org.ordina.coldchain.perishable.NewContract");
        contractBody.add("DevEUI", DevEUI);
        contractBody.add("sender", sender);
        contractBody.add("receiver", receiver);
        contractBody.add("minTemperature", minTemperature);
        contractBody.add("maxTemperature", maxTemperature);
        contractBody.add("transporter", transporter);
        contractBody.add("timestamp", sdf.format(new Date()));
        contractBody.add("description", description);

        blockchainDataService.sendToApi(contractBody, "http://hyperledger.vanhool.net:3000/api/org.ordina.coldchain.perishable.NewContract");


        return contractBody;
    }

    @RequestMapping(value = "changeResponsibility", method = RequestMethod.POST)
    public MultiValueMap<String, String> pushToResponsibilityAPI(
            @RequestParam(value = "DevEUI") String DevEUI,
            @RequestParam(value = "responsible") String responsible) {

        MultiValueMap<String, String> Responsibility = new LinkedMultiValueMap<>();
        Responsibility.add("$class", "org.ordina.coldchain.perishable.ResponsibilityChange");
        Responsibility.add("DevEUI", DevEUI);
        Responsibility.add("responsible", responsible);
        Responsibility.add("timestamp", sdf.format(new Date()));

        blockchainDataService.sendToApi(Responsibility, "http://hyperledger.vanhool.net:3000/api/org.ordina.coldchain.perishable.ResponsibilityChange");


        return Responsibility;
    }

    @RequestMapping(value = "acceptContract", method = RequestMethod.POST)
    public MultiValueMap<String, String> pushToAcceptAPI(
            @RequestParam(value = "DevEUI") String DevEUI) {

        MultiValueMap<String, String> accept = new LinkedMultiValueMap<>();
        accept.add("$class", "org.ordina.coldchain.perishable.FinishContract");
        accept.add("DevEUI", DevEUI);
        accept.add("timestamp", sdf.format(new Date()));

        blockchainDataService.sendToApi(accept, "http://hyperledger.vanhool.net:3000/api/org.ordina.coldchain.perishable.FinishContract");
        System.out.println("Accepting Contract");

        return accept;
    }

    @RequestMapping(value = "DenyContract", method = RequestMethod.POST)
    public MultiValueMap<String, String> pushToDenieAPI(
            @RequestParam(value = "DevEUI") String DevEUI,
            @RequestParam(value = "reason") String reason) {

        MultiValueMap<String, String> denie = new LinkedMultiValueMap<>();
        denie.add("$class", "org.ordina.coldchain.perishable.DenyContract");
        denie.add("DevEUI", DevEUI);
        denie.add("reason", reason);
        denie.add("timestamp", sdf.format(new Date()));

        blockchainDataService.sendToApi(denie, "http://hyperledger.vanhool.net:3000/api/org.ordina.coldchain.perishable.DenyContract");
        System.out.println("Denying Contract " + reason);
        return denie;
    }
}
