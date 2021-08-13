package mk.ukim.finki.emt.deviceshop.web;

import mk.ukim.finki.emt.deviceshop.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.emt.deviceshop.models.Device;
import mk.ukim.finki.emt.deviceshop.models.Manufacturer;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/device")
public class DeviceController {

    private Long counter;
    private List<Device> deviceList = null;

    private List<Manufacturer> manufacturers = null;

    @PostConstruct
    public void init() {
        counter = 1l;
        manufacturers = new ArrayList<>();
        Manufacturer m = new Manufacturer();
        m.setId(1l);
        m.setName("Samsung");
        manufacturers.add(m);

        deviceList = new ArrayList<>();
        Device d1 = new Device();
        d1.setId(getNextId());
        d1.setName("Galaxy S5");
        d1.setPrice(2000.0);
        deviceList.add(d1);

        Device d2 = new Device();
        d2.setId(getNextId());
        d2.setName("Mi");
        d2.setPrice(2000.0);
        deviceList.add(d2);
    }

    @GetMapping("/")
    public String devices(Model model) {
        model.addAttribute("deviceList",deviceList);
        model.addAttribute("manufacturerList",manufacturers);
        model.addAttribute("device", new Device());
        return "devices.form";
    }

    @ExceptionHandler({ManufacturerNotFoundException.class})
    @PostMapping("/")
    public String addDevice(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        Long manId = Long.parseLong(request.getParameter("manufacturerId"));
        Optional<Manufacturer> man = manufacturers.stream().filter(v->{
            return v.getId().equals(manId);
        }).findAny();
        if (!man.isPresent()) {
            throw new ManufacturerNotFoundException();
        }

        Device newDevice=  new Device();
        newDevice.setId(getNextId());
        newDevice.setName(name);
        newDevice.setPrice(price);
        newDevice.setManufacturer(man.get());

        deviceList.add(newDevice);
        model.addAttribute("deviceList",deviceList);
        return "redirect:/device/";

    }

    @ExceptionHandler({ManufacturerNotFoundException.class})
    @PostMapping("/add")
    public String addDeviceWithModelAttribute(@ModelAttribute Device d, Model model,
                                              @RequestParam("image") MultipartFile file) throws IOException {

        Optional<Manufacturer> man = manufacturers.stream().filter(v->{
            return v.getId().equals(d.getManufacturer().getId());
        }).findAny();
        if (!man.isPresent()) {
            throw new ManufacturerNotFoundException();
        }
        d.setPhoto(file.getBytes());
        d.setId(getNextId());
        d.setManufacturer(man.get());
        deviceList.add(d);
        return "redirect:/device/";
    }
    @DeleteMapping("/")
    public String deleteDevice(HttpServletRequest request) {
        Long deviceId = Long.parseLong(request.getParameter("deviceId"));
        deviceList.removeIf(v-> {
            return v.getId().equals(deviceId);
        });
        return "redirect:/device/";
    }

    @RequestMapping(value = "/image/{device_id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("device_id") Long deviceId) throws IOException {

        Optional<Device> man = deviceList.stream().filter(v->{
            return v.getId().equals(deviceId);
        }).findAny();
        if (!man.isPresent()) {
            throw new ManufacturerNotFoundException();
        }


        byte[] imageContent = man.get().getPhoto();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.parse("attachment; filename="+ man.get().getName()));
        headers.setContentLength(imageContent.length);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(imageContent.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(imageContent);
    }

    private Long getNextId() {
        return counter++;
    }
}
