package org.MiniSurveyMonkey.Repo;


import org.MiniSurveyMonkey.Model.OpenEnded;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Oeq", path = "Oeq")
public interface OpenEndedRepository extends CrudRepository<OpenEnded, Integer> {
    OpenEnded findById(@Param("id") int id);
}
