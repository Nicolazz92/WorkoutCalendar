package com.velikokhatko.study.service;

import com.velikokhatko.study.model.Contest;
import com.velikokhatko.study.repository.ContestRepository;
import com.velikokhatko.study.view.dto.ContestDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContestService {

    private final ContestRepository contestRepository;
    private final ConversionService conversionService;

    public ContestService(ContestRepository contestRepository,
                          @Qualifier("dtoConverter") ConversionService conversionService) {
        this.contestRepository = contestRepository;
        this.conversionService = conversionService;
    }

    @Transactional(readOnly = true)
    public Contest getContestById(Long contestId) {
        return contestRepository.findById(contestId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public ContestDTO getContestDTOById(Long workoutId) {
        return conversionService.convert(getContestById(workoutId), ContestDTO.class);
    }

    @Transactional(readOnly = true)
    public List<BaseEntityNamedDTO> getContestDTOs(String... sortByFields) {
        return getContests(sortByFields).stream()
                .map(user -> conversionService.convert(user, BaseEntityNamedDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Contest> getContests(String... sortByFields) {
        return contestRepository.findAll((root, query, criteriaBuilder) -> criteriaBuilder.and(), Sort.by(sortByFields));
    }
}
