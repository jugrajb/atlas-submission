package com.atlas.service;

import com.atlas.dao.PersonCardDAO;
import com.atlas.model.Condition;
import com.atlas.model.PersonCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonCardService {
    private PersonCardDAO personCardDAO;

    @Autowired
    public PersonCardService(@Qualifier("postgres-personcard") PersonCardDAO personCardDAO) {
        this.personCardDAO = personCardDAO;
    }

    public Optional<PersonCard> get(int id) {
        return personCardDAO.get(id);
    }

    public List<PersonCard> getAll() {
        return personCardDAO.getAll();
    }

    public List<PersonCard> getWithCondition(Condition condition) {
        // check condition non-null, otherwise ignore
        if (condition.getAttribute() == null || condition.getComparator() == null || condition.getValue() == null) {
            return personCardDAO.getAll();
        }
        return personCardDAO.getWithCondition(condition);
    }
}
