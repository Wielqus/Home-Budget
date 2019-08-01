/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeBudget.java.model.tableview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Wielq
 */
public class Filter {

    public enum filterType {
        textFilter, doubleFilter, listFilter, intFilter, dateLessFilter, dateMoreFilter,doubleLessFilter,doubleMoreFilter,
    };

    public String column;

    public filterType type;

    public String value;

    public Filter(String column, filterType type) {
        this.column = column;
        this.type = type;
    }

    /**
     * Add filters to hibernate query
     * @param criteria
     * @param filtersList
     * @return
     */
    public static Criteria implmentFilters(Criteria criteria, ArrayList<ArrayList<Filter>> filtersList) {

        filtersList.forEach(filters -> {
            filters.forEach(filter -> {
                if (filter.value != "" && filter.value != null) {
                    if (filter.type == Filter.filterType.textFilter) {
                        criteria.add(Restrictions.like(filter.column, "%" + filter.value + "%"));
                    }
                    if (filter.type == Filter.filterType.doubleFilter) {
                        criteria.add(Restrictions.eq(filter.column, Double.parseDouble(filter.value)));
                    }
                    if (filter.type == Filter.filterType.listFilter) {
                        if (filter.value != "0") {
                            criteria.add(Restrictions.eq(filter.column, Integer.parseInt(filter.value)));
                        }

                    }
                    if (filter.type == Filter.filterType.dateLessFilter) {
                        if (filter.value!=null) {                            
                            criteria.add(Restrictions.ge(filter.column, java.sql.Date.valueOf(filter.value)));
                        }
                    }
                    if (filter.type == Filter.filterType.dateMoreFilter) {
                        if (filter.value!=null) {
                            criteria.add(Restrictions.le(filter.column, java.sql.Date.valueOf(filter.value)));
                        }
                    }
                    if (filter.type == Filter.filterType.doubleLessFilter) {
                        if (filter.value != "") {                       
                            criteria.add(Restrictions.ge(filter.column, Double.parseDouble(filter.value)));
                        }
                    }
                    if (filter.type == Filter.filterType.doubleMoreFilter) {
                        if (filter.value != "") {
                            criteria.add(Restrictions.le(filter.column, Double.parseDouble(filter.value)));
                        }
                    }
                }

            });
        });

        return criteria;
    }

}
