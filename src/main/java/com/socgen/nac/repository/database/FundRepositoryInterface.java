package com.socgen.nac.repository.database;

import com.socgen.nac.entity.source.Fund;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepositoryInterface extends CrudRepository<Fund, String> {

    //Iterable<Fund> findbyteam(String valuationTeam);

    //Nom de la méthode après 'findBy' doit OBLIGATOIREMENT avoir le nom des variables
    //Iterable<Fund> findByValuationTeam(String valuationTeam); -> Recherche par valuation team -> nom du champs en entrée: valuation team

    //Si plusieurs paramètres, séparer avec le mot clé 'And'
    //ATTENTION: L'ordre des champs est important -> ici la méthode s'attend à avoir la valeur de valuationTeam en premier et fundCode en second
    //Iterable<Fund> findByValuationTeamAndFundCode(String valuationTeam, String fundCode);

    //Iterable<Fund>findFundByTeam(String valuationTeam);
}
