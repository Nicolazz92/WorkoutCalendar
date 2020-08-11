package com.velikokhatko.study.service.mapper;

import com.velikokhatko.study.view.dto.ContestDTO;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Node;

class ContestDTOMappingServiceTest extends AbstractMapperTest {

    ContestDTOMappingService mappingService = new ContestDTOMappingService();

    @Test
    void entityToDTO() {
        ContestDTO contestDTO = mappingService.entityToDTO(bicycleContest);

        Yaml yaml = new Yaml();
        Node represent = yaml.represent(contestDTO);

        System.out.println();
    }

    @Test
    void dtoToEntity() {
    }
}