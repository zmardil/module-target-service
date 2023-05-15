package com.ihub.orderservice.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderIdGenerator implements IdentifierGenerator {

    @Override
    public String generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "OD";
        String query = String.format(
            "SELECT %s FROM %s ORDER BY %s DESC", "id", object.getClass().getSimpleName(), "id"
        );
        List<String> maxIdList = session
                .createQuery(query, String.class)
                .setMaxResults(1)
                .getResultList();
        if(maxIdList.isEmpty()) return prefix + "0001";
        String maxId = maxIdList.get(0);
        int id = Integer.parseInt(maxId.substring(2));
        String newId = String.format("%04d", id + 1);
        return prefix + newId;
    }
}
