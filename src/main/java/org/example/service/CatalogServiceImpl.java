package org.example.service;

import org.example.models.Place;
import org.example.repositories.PlaceRepository;

import java.sql.SQLException;
import java.util.List;

public class CatalogServiceImpl implements CatalogService {
    private final PlaceRepository placeRepository = new PlaceRepository();

    @Override
    public List<Place> getAllPlaces() throws SQLException {
        return placeRepository.findAll();
    }
}
