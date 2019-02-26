package be.ordina.coldchain.controller;



import be.ordina.coldchain.model.Bedrijfsvorm;
import be.ordina.coldchain.repository.BedrijfsvormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200", "http://chainreact-af.s3-website.eu-west-3.amazonaws.com/"})
@RestController
@RequestMapping(value = {"/bedrijfsvormen", "/bedrijfsvorms"})
public class BedrijfsvormController {

    @Autowired
    private BedrijfsvormRepository bedrijfsvormRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Bedrijfsvorm> getTypes() {
        List<Bedrijfsvorm> vormen = new ArrayList<>();
        bedrijfsvormRepository.findAll().forEach(vormen::add);

        return vormen;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addType(@RequestParam(value = "vorm") String vorm) {
        Bedrijfsvorm bedrijfsvorm = new Bedrijfsvorm();
        bedrijfsvorm.setVorm(vorm);
        bedrijfsvorm.setId(UUID.randomUUID().toString());

        bedrijfsvormRepository.save(bedrijfsvorm);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteType(@RequestParam(value = "id") String id) {
        bedrijfsvormRepository.deleteById(id);
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH)
    public void patchTpe(@RequestParam(value = "id") String id,
                         @RequestParam(value = "version") int version,
                         @RequestParam(value = "vorm") String vorm) {
        Bedrijfsvorm type = new Bedrijfsvorm();
        type.setId(id);
        type.setVersion(version);
        type.setVorm(vorm);

        bedrijfsvormRepository.save(type);
    }

    public Bedrijfsvorm getBedrijfsvormById(String id) {
        return bedrijfsvormRepository.findById(id);
    }

}
