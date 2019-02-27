/*
 Author:Roby de Visser
*/

package be.ordina.coldchain.controller;

import be.ordina.coldchain.model.Account;
import be.ordina.coldchain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200", "http://chainreact-af.s3-website.eu-west-3.amazonaws.com/"})
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTypeController accountTypeController;

    @Autowired
    private BedrijfsvormController bedrijfsvormController;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Account addAccount(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "bedrijfsnaam") String bedrijfsnaam,
            @RequestParam(value = "accountTypeId") String accountTypeId,
            @RequestParam(value = "straat") String straat,
            @RequestParam(value = "huisnummer") int huisnummer,
            @RequestParam(value = "stad") String stad,
            @RequestParam(value = "postcode") int postcode,
            @RequestParam(value = "bedrijfsvormId") String bedrijfsvormId,
            @RequestParam(value = "btwNummer") String btwNummer) {
        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        account.setBedrijfsnaam(bedrijfsnaam);
        account.setAccountType(accountTypeController.getAccountTypeById(accountTypeId));
        account.setStraat(straat);
        account.setHuisnummer(huisnummer);
        account.setStad(stad);
        account.setPostcode(postcode);
        account.setBedrijfsvorm(bedrijfsvormController.getBedrijfsvormById(bedrijfsvormId));
        account.setBtwNummer(btwNummer);

        return accountRepository.save(account);
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH)
    public Account patchAccount(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "bedrijfsnaam") String bedrijfsnaam,
            @RequestParam(value = "accountTypeId") String accountTypeId,
            @RequestParam(value = "straat") String straat,
            @RequestParam(value = "huisnummer") int huisnummer,
            @RequestParam(value = "stad") String stad,
            @RequestParam(value = "postcode") int postcode,
            @RequestParam(value = "bedrijfsvormId") String bedrijfsvormId,
            @RequestParam(value = "btwNummer") String btwNummer,
            @RequestParam(value = "confirmPassword") String confirmPassword,
            @RequestParam(value = "version") int version) {
        String pwCheck = getPassword(id);
        if (passwordEncoder.matches(confirmPassword, pwCheck)) {
            Account account = new Account();
            account.setVersion(version);
            account.setId(id);
            account.setEmail(email);
            account.setPassword(passwordEncoder.encode(password));
            account.setBedrijfsnaam(bedrijfsnaam);
            account.setAccountType(accountTypeController.getAccountTypeById(accountTypeId));
            account.setStraat(straat);
            account.setHuisnummer(huisnummer);
            account.setStad(stad);
            account.setPostcode(postcode);
            account.setBedrijfsvorm(bedrijfsvormController.getBedrijfsvormById(bedrijfsvormId));
            account.setBtwNummer(btwNummer);

            if (password.equals("")) {
                account.setPassword(pwCheck);
            } else {
                account.setPassword(passwordEncoder.encode(password));
            }
            account = accountRepository.save(account);
            account.setPassword("");
            return account;
        }
        return new Account();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") String id) {
        Account account = new Account();
        account = accountRepository.findById(id);
        account.setPassword("");
        return account;
    }

    //@RequestMapping(value = "", method = RequestMethod.GET)
    public List<Account> getAccountsempty() {
        return new ArrayList<>();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Account login(@RequestParam(value = "email") String email,
                         @RequestParam(value = "password") String password) {
        Account account = accountRepository.getAccountByEmail(email);

        if (passwordEncoder.matches(password, account.getPassword())) {
            account.setPassword("");
            return account;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public Account getAccountsByEmail(
            @PathVariable(value = "email", required = false) String email) {

        Account account;
        account = accountRepository.findByEmail(email);
        account.setPassword("");
        return account;
    }

    private String getPassword(String id) {
        Account account;
        account = accountRepository.findById(id);
        return account.getPassword();
    }


}