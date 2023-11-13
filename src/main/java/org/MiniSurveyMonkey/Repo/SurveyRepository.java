package org.MiniSurveyMonkey.Repo;

import org.MiniSurveyMonkey.Model.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "surveys", path = "surveys")
public interface SurveyRepository extends CrudRepository<Survey, Integer> {
    Survey findById(@Param("id") int id);
}
