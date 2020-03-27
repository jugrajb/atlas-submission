package com.atlas.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserFilterDAO {
    Optional<Map<String, Object>> get(int id, List<String> columns);

    List<Map<String, Object>> getAll(List<String> columns);
}
