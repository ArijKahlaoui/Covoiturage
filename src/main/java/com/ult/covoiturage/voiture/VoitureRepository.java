package com.ult.covoiturage.voiture;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//Remove @RepositoryRestResource below to disable auto REST api:
@RepositoryRestResource
public interface VoitureRepository extends CrudRepository<Voiture, Integer>{}
