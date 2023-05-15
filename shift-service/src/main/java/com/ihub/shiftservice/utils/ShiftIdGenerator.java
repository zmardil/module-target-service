package com.ihub.shiftservice.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShiftIdGenerator implements IdentifierGenerator {

    @Override
    public String generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "SHF";
        String query = String.format(
            "SELECT %s FROM %s ORDER BY %s DESC", "id", object.getClass().getSimpleName(), "id"
        );
        List<String> maxIdList = session
                .createQuery(query, String.class)
                .setMaxResults(1)
                .getResultList();
        if(maxIdList.isEmpty()) return prefix + "000001";
        String maxId = maxIdList.get(0);
        int id = Integer.parseInt(maxId.substring(3));
        String newId = String.format("%06d", id + 1);
        return prefix + newId;
    }
}
