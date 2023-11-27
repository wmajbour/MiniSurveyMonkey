package org.MiniSurveyMonkey.Repo;


import org.MiniSurveyMonkey.Model.NumRange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Nrq", path = "Nrq")
public interface NumRangeRepository extends CrudRepository<NumRange, Integer> {
    NumRange findById(@Param("id") int id);
}
