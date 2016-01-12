package cworks.hipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import cworks.hipster.domain.App;
import cworks.hipster.service.AppService;
import cworks.hipster.web.rest.util.HeaderUtil;
import cworks.hipster.web.rest.dto.AppDTO;
import cworks.hipster.web.rest.mapper.AppMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing App.
 */
@RestController
@RequestMapping("/api")
public class AppResource {

    private final Logger log = LoggerFactory.getLogger(AppResource.class);
        
    @Inject
    private AppService appService;
    
    @Inject
    private AppMapper appMapper;
    
    /**
     * POST  /apps -> Create a new app.
     */
    @RequestMapping(value = "/apps",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AppDTO> createApp(@Valid @RequestBody AppDTO appDTO) throws URISyntaxException {
        log.debug("REST request to save App : {}", appDTO);
        if (appDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("app", "idexists", "A new app cannot already have an ID")).body(null);
        }
        AppDTO result = appService.save(appDTO);
        return ResponseEntity.created(new URI("/api/apps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("app", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /apps -> Updates an existing app.
     */
    @RequestMapping(value = "/apps",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AppDTO> updateApp(@Valid @RequestBody AppDTO appDTO) throws URISyntaxException {
        log.debug("REST request to update App : {}", appDTO);
        if (appDTO.getId() == null) {
            return createApp(appDTO);
        }
        AppDTO result = appService.save(appDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("app", appDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /apps -> get all the apps.
     */
    @RequestMapping(value = "/apps",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<AppDTO> getAllApps() {
        log.debug("REST request to get all Apps");
        return appService.findAll();
            }

    /**
     * GET  /apps/:id -> get the "id" app.
     */
    @RequestMapping(value = "/apps/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AppDTO> getApp(@PathVariable Long id) {
        log.debug("REST request to get App : {}", id);
        AppDTO appDTO = appService.findOne(id);
        return Optional.ofNullable(appDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /apps/:id -> delete the "id" app.
     */
    @RequestMapping(value = "/apps/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteApp(@PathVariable Long id) {
        log.debug("REST request to delete App : {}", id);
        appService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("app", id.toString())).build();
    }
}
