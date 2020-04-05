package com.atlas.dao;

import java.util.List;
import java.util.Map;

public interface NaturalJoinDAO {
    List<Map<String, Object>> get(String relation1, String relation2);
}
