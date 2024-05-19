package yuri.filgueira.yufoodapi.mapper.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yuri.filgueira.yufoodapi.mapper.modelMapper.config.ModelMapperConfig;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyModelMapper {

    private static final ModelMapper modelMapper = new ModelMapperConfig().modelMapper();

    public static <O, D> D convertValue(O origin, Class<D> destination) {

        return modelMapper.map(origin, destination);
    }

    public static <O, D> List<D> convertList(List<O> origins, Class<D> destination) {

        List<D> destinations = new ArrayList<>();

        origins.stream().map(origin -> convertValue(origin, destination)).forEach(destinations::add);
        return destinations;
    }

}
