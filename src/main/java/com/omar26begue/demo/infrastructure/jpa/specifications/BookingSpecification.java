package com.omar26begue.demo.infrastructure.jpa.specifications;

import com.omar26begue.demo.infrastructure.jpa.model.BookingDAO;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class BookingSpecification {

    public static Specification<BookingDAO> filter(UUID idRoom, LocalDate date) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("cancelled"), false));

            if (idRoom != null) {
                predicates.add(cb.equal(root.get("room").get("id"), idRoom));
            }

            if (date != null) {
                var dateStartOfDay = date.atStartOfDay();
                var dateEndOfDay = date.atTime(LocalTime.MAX);
                predicates.add(cb.and(
                        cb.lessThanOrEqualTo(root.get("dateStart"), dateEndOfDay),
                        cb.greaterThanOrEqualTo(root.get("dateEnd"), dateStartOfDay)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
