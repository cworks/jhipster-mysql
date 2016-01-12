package cworks.hipster.service;

import cworks.hipster.domain.App;
import cworks.hipster.web.rest.dto.AppDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing App.
 */
public interface AppService {

    /**
     * Save a app.
     * @return the persisted entity
     */
    public AppDTO save(AppDTO appDTO);

    /**
     *  get all the apps.
     *  @return the list of entities
     */
    public List<AppDTO> findAll();

    /**
     *  get the "id" app.
     *  @return the entity
     */
    public AppDTO findOne(Long id);

    /**
     *  delete the "id" app.
     */
    public void delete(Long id);
}
