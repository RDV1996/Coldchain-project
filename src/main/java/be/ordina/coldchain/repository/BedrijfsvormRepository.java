/*
 Author:Roby de Visser
*/

package be.ordina.coldchain.repository;


import be.ordina.coldchain.model.Bedrijfsvorm;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BedrijfsvormRepository extends CrudRepository<Bedrijfsvorm, Long> {

    Bedrijfsvorm findById(String id);

    Bedrijfsvorm deleteById(String id);

}