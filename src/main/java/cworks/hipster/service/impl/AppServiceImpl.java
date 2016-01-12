package cworks.hipster.service.impl;

import cworks.hipster.service.AppService;
import cworks.hipster.domain.App;
import cworks.hipster.repository.AppRepository;
import cworks.hipster.web.rest.dto.AppDTO;
import cworks.hipster.web.rest.mapper.AppMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing App.
 */
@Service
@Transactional
public class AppServiceImpl implements AppService{

    private final Logger log = LoggerFactory.getLogger(AppServiceImpl.class);
    
    @Inject
    private AppRepository appRepository;
    
    @Inject
    private AppMapper appMapper;
    
    /**
     * Save a app.
     * @return the persisted entity
     */
    public AppDTO save(AppDTO appDTO) {
        log.debug("Request to save App : {}", appDTO);
        App app = appMapper.appDTOToApp(appDTO);
        app = appRepository.save(app);
        AppDTO result = appMapper.appToAppDTO(app);
        return result;
    }

    /**
     *  get all the apps.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<AppDTO> findAll() {
        log.debug("Request to get all Apps");
        List<AppDTO> result = appRepository.findAll().stream()
            .map(appMapper::appToAppDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one app by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public AppDTO findOne(Long id) {
        log.debug("Request to get App : {}", id);
        App app = appRepository.findOne(id);
        AppDTO appDTO = appMapper.appToAppDTO(app);
        return appDTO;
    }

    /**
     *  delete the  app by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete App : {}", id);
        appRepository.delete(id);
    }
}
