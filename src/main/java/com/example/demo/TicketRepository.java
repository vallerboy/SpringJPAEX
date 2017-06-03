package com.example.demo;

import com.example.demo.models.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by OskarPraca on 2017-05-31.
 */

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
        Optional<Ticket> findOne(int id);
        List<Ticket> findByAuthor(String author);
        List<Ticket> findByMessageLike(String prefix);

        @Query(value = "SELECT * FROM ticket WHERE author = ?1 AND cos = ?2 LIMIT 1", nativeQuery = true)
        Ticket getTicket(String person, String cos);
}
