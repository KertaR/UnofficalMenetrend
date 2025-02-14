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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @ModelAttribute("todayDate")
    public String todayDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return today.format(formatter);
    }

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @PostMapping("/")
    public String menetrend(
            @RequestParam("kezdoAllomas") String kezdoAllomas,
            @RequestParam("vegAllomas") String vegAllomas,
            @RequestParam("indulasiIdo") String indulasiIdo, Model model)
    {

        if (kezdoAllomas == null || kezdoAllomas.isEmpty()) {
            model.addAttribute("error", "Indulási hely kitöltése kötelező.");
        }

        if (vegAllomas == null || vegAllomas.isEmpty()) {
            model.addAttribute("error", "Érkezési hely kitöltése kötelező.");
        }

        if (indulasiIdo == null || indulasiIdo.isEmpty()) {
            model.addAttribute("error", "Indulási idő kitöltése kötelező.");
        }
        List<String> cities = cities();
        if(!cities.contains(kezdoAllomas)) {
            model.addAttribute("error", "Helytelen Indulási hely.");
        }
        if(!cities.contains(vegAllomas)) {
            model.addAttribute("error", "Helytelen Érkezési hely.");
        }

        if(!model.containsAttribute("error"))
        {
            Results results = menetrendService.getMenetrendek(kezdoAllomas, vegAllomas, indulasiIdo);
            model.addAttribute("results", results);
        }
        model.addAttribute("kezdoAllomas", kezdoAllomas);
        model.addAttribute("vegAllomas", vegAllomas);
        return "index";
    }
}
