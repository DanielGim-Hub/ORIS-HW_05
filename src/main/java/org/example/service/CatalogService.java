package org.example.service;

import org.example.models.Place;

import java.sql.SQLException;
import java.util.List;

public interface CatalogService {
    List<Place> getAllPlaces() throws SQLException;
}
