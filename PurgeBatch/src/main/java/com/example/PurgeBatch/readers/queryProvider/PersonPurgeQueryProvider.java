package com.example.PurgeBatch.readers.queryProvider;

import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;

import java.util.HashMap;
import java.util.Map;

public class PersonPurgeQueryProvider {
    public PostgresPagingQueryProvider queryProvider(){
        PostgresPagingQueryProvider provider=new PostgresPagingQueryProvider();

        Map<String, Order> sortKeys=new HashMap<String,Order>();

        sortKeys.put("id",Order.ASCENDING);

        provider.setSelectClause("select *");
        provider.setFromClause("from person");
        provider.setWhereClause("where role='CEO'");
        provider.setSortKeys(sortKeys);

        return provider;


    }
}
