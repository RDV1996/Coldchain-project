/**
 * Author: Jorne Lambrechts
 */
package be.ordina.coldchain.repository;

import be.ordina.coldchain.model.AccountType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {

    AccountType findById(String id);

    AccountType deleteById(String id);


}
