package hu.kertar.unofficalmenetrend.controller;

import hu.kertar.api.getroutes.Results;
import hu.kertar.unofficalmenetrend.mapper.CityMapper;
import hu.kertar.unofficalmenetrend.service.MenetrendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @ModelAttribute("cities")
    public List<String> cities() {
        return CityMapper.getAllCities();
    }

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @PostMapping("/")
    public String menetrend(@RequestParam("kezdoAllomas") String kezdoAllomas, @RequestParam("vegAllomas") String vegAllomas, Model model)
    {

        if (kezdoAllomas == null || kezdoAllomas.isEmpty()) {
            model.addAttribute("error", "Indulási hely kitöltése kötelező.");
        }

        if (vegAllomas == null || vegAllomas.isEmpty()) {
            model.addAttribute("error", "Érkezési hely kitöltése kötelező.");
        }

        Results results = menetrendService.getMenetrendek(kezdoAllomas, vegAllomas);
        model.addAttribute("results", results);
        return "index";
    }
}
