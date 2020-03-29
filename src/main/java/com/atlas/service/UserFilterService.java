package com.atlas.service;

import com.atlas.dao.UserFilterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserFilterService {
    private UserFilterDAO userFilterDAO;
    private Set<String> allowedColumns;

    @Autowired
    public UserFilterService(@Qualifier("postgres-userfilter") UserFilterDAO userFilterDAO) {
        this.userFilterDAO = userFilterDAO;
        this.allowedColumns = new HashSet<>();
        Collections.addAll(allowedColumns, "uid", "email", "password", "username");
    }

    public Optional<Map<String, Object>> get(int id, List<String> columns) {
        return userFilterDAO.get(id, filterColumns(columns));
    }

    public List<Map<String, Object>> getAll(List<String> columns) {
        return userFilterDAO.getAll(filterColumns(columns));
    }

    private List<String> filterColumns(List<String> columns) {
        if (columns == null)
            return Arrays.asList("");

        List<String> filtered = new ArrayList<>();
        for (String col : columns) {
            if (allowedColumns.contains(col)) {
                filtered.add(col);
            }
        }
        return filtered;
    }
}
