package org.MiniSurveyMonkey.Repo;


import org.MiniSurveyMonkey.Model.MultipleChoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Mcq", path = "Mcq")
public interface MultipleChoiceRepository extends CrudRepository<MultipleChoice, Integer> {
    MultipleChoice findById(@Param("id") int id);
}
