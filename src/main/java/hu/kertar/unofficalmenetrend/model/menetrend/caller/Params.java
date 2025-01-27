package hu.kertar.unofficalmenetrend.model.menetrend.caller;

import lombok.Data;

@Data
public class Params {
    private String datum;
    private String erk_stype;
    private String ext_settings;
    private int filtering;
    private String helyi;
    private String honnan;
    private int honnan_ls_id;
    private int honnan_settlement_id;
    private String honnan_site_code;
    private String hour;
    private String hova;
    private int hova_ls_id;
    private int hova_settlement_id;
    private String hova_site_code;
    private String ind_stype;
    private String keresztul_stype;
    private String maxatszallas;
    private String maxvar;
    private String maxwalk;
    private String min;
    private int napszak;
    private int naptipus;
    private int odavissza;
    private String preferencia;
    private String rendezes;
    private int submitted;
    private int talalatok;
    private int target;
    private String utirany;
    private String var;

}
