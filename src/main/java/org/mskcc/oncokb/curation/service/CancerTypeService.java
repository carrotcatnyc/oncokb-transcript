package org.mskcc.oncokb.curation.service;

import java.util.List;
import java.util.Optional;
import org.mskcc.oncokb.curation.domain.CancerType;
import org.mskcc.oncokb.curation.repository.CancerTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CancerType}.
 */
@Service
@Transactional
public class CancerTypeService {

    private final Logger log = LoggerFactory.getLogger(CancerTypeService.class);

    private final CancerTypeRepository cancerTypeRepository;

    public CancerTypeService(CancerTypeRepository cancerTypeRepository) {
        this.cancerTypeRepository = cancerTypeRepository;
    }

    /**
     * Save a cancerType.
     *
     * @param cancerType the entity to save.
     * @return the persisted entity.
     */
    public CancerType save(CancerType cancerType) {
        log.debug("Request to save CancerType : {}", cancerType);
        return cancerTypeRepository.save(cancerType);
    }

    /**
     * Partially update a cancerType.
     *
     * @param cancerType the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CancerType> partialUpdate(CancerType cancerType) {
        log.debug("Request to partially update CancerType : {}", cancerType);

        return cancerTypeRepository
            .findById(cancerType.getId())
            .map(existingCancerType -> {
                if (cancerType.getCode() != null) {
                    existingCancerType.setCode(cancerType.getCode());
                }
                if (cancerType.getColor() != null) {
                    existingCancerType.setColor(cancerType.getColor());
                }
                if (cancerType.getLevel() != null) {
                    existingCancerType.setLevel(cancerType.getLevel());
                }
                if (cancerType.getMainType() != null) {
                    existingCancerType.setMainType(cancerType.getMainType());
                }
                if (cancerType.getSubtype() != null) {
                    existingCancerType.setSubtype(cancerType.getSubtype());
                }
                if (cancerType.getTissue() != null) {
                    existingCancerType.setTissue(cancerType.getTissue());
                }
                if (cancerType.getTumorForm() != null) {
                    existingCancerType.setTumorForm(cancerType.getTumorForm());
                }

                return existingCancerType;
            })
            .map(cancerTypeRepository::save);
    }

    /**
     * Get all the cancerTypes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CancerType> findAll() {
        log.debug("Request to get all CancerTypes");
        return cancerTypeRepository.findAll();
    }

    /**
     * Get one cancerType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CancerType> findOne(Long id) {
        log.debug("Request to get CancerType : {}", id);
        return cancerTypeRepository.findById(id);
    }

    /**
     * Delete the cancerType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CancerType : {}", id);
        cancerTypeRepository.deleteById(id);
    }
}
