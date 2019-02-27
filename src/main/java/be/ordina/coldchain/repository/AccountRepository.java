/*
 Author:Roby de Visser
*/

package be.ordina.coldchain.repository;

import be.ordina.coldchain.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findById(String id);

    Account deleteById(String id);

    Account findByEmail(String email);

    Account getAccountByEmail(String email);
}
