package hu.kertar.unofficalmenetrend.service;

import hu.kertar.api.ApacheRestClient;
import hu.kertar.api.getroutes.GetRoutesRequest;
import hu.kertar.api.getroutes.GetRoutesResponse;
import hu.kertar.api.getroutes.Results;
import hu.kertar.unofficalmenetrend.mapper.CityMapper;
import org.springframework.stereotype.Service;

@Service
public class MenetrendService {

    private static final String MENETRENDEK_HU_URL = "https://menetrendek.hu/menetrend/newinterface/index.php";

    public Results getMenetrendek(String kezdoAllomas, String vegAllomas, String indulasiIdo) {

        int kezdoAllomasSettlementId = CityMapper.getSettlementIdByCityName(kezdoAllomas);
        int vegAllomasSettlementId = CityMapper.getSettlementIdByCityName(vegAllomas);

        ApacheRestClient apacheRestClient = new ApacheRestClient(MENETRENDEK_HU_URL);

        GetRoutesRequest getRoutesRequest = new GetRoutesRequest();
        getRoutesRequest.getParams().setDatum(indulasiIdo);
        getRoutesRequest.getParams().setHonnan(kezdoAllomas);
        getRoutesRequest.getParams().setHonnan_ls_id(0);
        getRoutesRequest.getParams().setHonnan_settlement_id(kezdoAllomasSettlementId);
        getRoutesRequest.getParams().setHour("1");
        getRoutesRequest.getParams().setHova(vegAllomas);
        getRoutesRequest.getParams().setHova_ls_id(0);
        getRoutesRequest.getParams().setHova_settlement_id(vegAllomasSettlementId);
        getRoutesRequest.getParams().setMin("27");
        getRoutesRequest.getParams().setNaptipus(0);
        getRoutesRequest.getParams().setPreferencia("0");
        getRoutesRequest.getParams().setVar("0");

        try {

            GetRoutesResponse response = apacheRestClient.postRequest("/", getRoutesRequest, GetRoutesResponse.class);

            if (response.getStatus().equals("success")) {
                return response.getResults();
            }
            else {
                throw new RuntimeException(response.getErrMsg());
            }
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
