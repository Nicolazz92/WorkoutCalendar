package com.velikokhatko.study.service;

import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.repository.TrackRepository;
import com.velikokhatko.study.view.dto.TrackDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackService {

    private final TrackRepository trackRepository;
    private final ConversionService conversionService;

    public TrackService(TrackRepository trackRepository,
                        @Qualifier("dtoConverter") ConversionService conversionService) {
        this.trackRepository = trackRepository;
        this.conversionService = conversionService;
    }

    @Transactional(readOnly = true)
    public List<Track> getTracks(String... sortByFields) {
        return trackRepository.findAll((root, query, criteriaBuilder) -> criteriaBuilder.and(), Sort.by(sortByFields));
    }

    @Transactional(readOnly = true)
    public Track getTrackById(Long trackId) {
        return trackRepository.findById(trackId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<BaseEntityNamedDTO> getTrackDTOs(String... sortByFields) {
        return getTracks(sortByFields).stream()
                .map(track -> conversionService.convert(track, BaseEntityNamedDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TrackDTO getTrackDTOById(Long trackId) {
        return conversionService.convert(getTrackById(trackId), TrackDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Track save(Track track) {
        return trackRepository.save(track);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long trackId) {
        trackRepository.deleteById(trackId);
    }
}
