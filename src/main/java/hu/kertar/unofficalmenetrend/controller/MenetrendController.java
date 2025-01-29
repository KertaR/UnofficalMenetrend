package hu.kertar.unofficalmenetrend.controller;

import com.google.gson.JsonArray;
import hu.kertar.unofficalmenetrend.model.menetrend.Menetrend;
import hu.kertar.unofficalmenetrend.service.CityMapper;
import hu.kertar.unofficalmenetrend.service.MenetrendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MenetrendController
{

    private final MenetrendService menetrendService;

    public MenetrendController(MenetrendService menetrendService) {
        this.menetrendService = menetrendService;
    }

    @GetMapping("/")
    public String index(Model model)
    {
        List<String> cities = CityMapper.getAllCities();
        model.addAttribute("cities", cities);
        model.addAttribute(new Menetrend());
        return "index";
    }

    @PostMapping("/")
    public String menetrend(@RequestParam("kezdoAllomas") String kezdoAllomas, @RequestParam("vegAllomas") String vegAllomas, Model model)
    {
        List<Menetrend> menetrendek = menetrendService.getMenetrendek(kezdoAllomas, vegAllomas);
        model.addAttribute("menetrendek", menetrendek);
        return "index";
    }
}
