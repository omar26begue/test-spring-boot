package com.omar26begue.demo.infrastructure.jpa.specifications;

import com.omar26begue.demo.infrastructure.jpa.model.BookingDAO;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class BookingSpecification {

    public static Specification<BookingDAO> filter() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("cancelled"), false));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
