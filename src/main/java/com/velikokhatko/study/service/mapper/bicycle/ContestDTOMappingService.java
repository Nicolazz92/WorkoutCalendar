package com.velikokhatko.study.service.mapper.bicycle;

import com.velikokhatko.study.model.Contest;
import com.velikokhatko.study.model.base.BaseEntityNamed;
import com.velikokhatko.study.service.mapper.bicycle.base.BaseNamedMappingService;
import com.velikokhatko.study.view.dto.ContestDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContestDTOMappingService extends BaseNamedMappingService<Contest, ContestDTO> {

    @Override
    public ContestDTO entityToDTO(Contest entity) {
        List<BaseEntityNamedDTO> members = entity.getMembers().stream()
                .map(workout -> ((BaseEntityNamed) workout))
                .map(this::entityToBaseEntityNamedDTO)
                .collect(Collectors.toList());

        BaseEntityNamedDTO track = entityToBaseEntityNamedDTO(entity.getTrack());
        BaseEntityNamedDTO winner = entityToBaseEntityNamedDTO(entity.getWinner());

        return ContestDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .date(entity.getDate())
                .track(track)
                .members(members)
                .winner(winner)
                .build();
    }

    @Override
    public Contest dtoToEntity(ContestDTO dto) {
        throw new UnsupportedOperationException("Not Implemented Yet");
    }
}
