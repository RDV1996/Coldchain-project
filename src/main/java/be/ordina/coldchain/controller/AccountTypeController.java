/**
 * Managing account types
 * Author: Jorne Lambrechts
 */
package be.ordina.coldchain.controller;

import be.ordina.coldchain.model.AccountType;
import be.ordina.coldchain.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200", "http://chainreact-af.s3-website.eu-west-3.amazonaws.com/"})
@RestController
@RequestMapping(value = "/accountTypes")
public class AccountTypeController {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<AccountType> getTypes() {
        List<AccountType> types = new ArrayList<>();
        accountTypeRepository.findAll().forEach(types::add);

        return types;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addType(@RequestParam(value = "naam") String naam) {
        AccountType type = new AccountType();
        type.setId(UUID.randomUUID().toString());
        type.setNaam(naam);

        accountTypeRepository.save(type);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteType(@RequestParam(value = "id") String id) {
        accountTypeRepository.deleteById(id);
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH)
    public void patchTpe(@RequestParam(value = "id") String id,
                         @RequestParam(value = "version") int version,
                         @RequestParam(value = "naam") String naam) {
        AccountType type = new AccountType();
        type.setId(id);
        type.setVersion(version);
        type.setNaam(naam);

        accountTypeRepository.save(type);
    }


    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public AccountType getAccountTypeById(@PathVariable("id") String id) {
        return accountTypeRepository.findById(id);
    }
}
