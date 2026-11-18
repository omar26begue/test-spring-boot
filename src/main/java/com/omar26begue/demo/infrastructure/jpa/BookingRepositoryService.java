package com.omar26begue.demo.infrastructure.jpa;

import com.omar26begue.demo.domain.entities.BookingDomain;
import com.omar26begue.demo.domain.mappers.BookingMapper;
import com.omar26begue.demo.domain.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
@AllArgsConstructor
public class BookingRepositoryService implements BookingRepository {

    private final BookingMapper mapper;
    private final BookingJPARepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<BookingDomain> findConflictingBookings(UUID roomId, LocalDateTime dateStart, LocalDateTime dateEnd) {
        var conflicts = repository.findConflictingBookings(roomId, dateStart, dateEnd);
        return conflicts.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingDomain> paginated(Integer page, Integer size) {
        var pageRequest = PageRequest.of(page - 1, size);
        var response = repository.findAll(pageRequest);
        return response.map(mapper::toDomain);
    }

    @Override
    public BookingDomain save(BookingDomain payload) {
        var entity = mapper.toEntity(payload);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}
