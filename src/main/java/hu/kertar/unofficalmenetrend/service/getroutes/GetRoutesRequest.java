package hu.kertar.unofficalmenetrend.service.getroutes;

public class GetRoutesRequest {
    private String func = "getRoutes";
    private Params params = new Params();

    public String getFunc() {
        return func;
    }

    public Params getParams() {
        return params;
    }
}
