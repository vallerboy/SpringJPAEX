package com.example.demo.controllers;

import com.example.demo.TicketRepository;
import com.example.demo.UserRepository;
import com.example.demo.models.Ticket;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by OskarPraca on 2017-05-31.
 */

@Controller
public class MainController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/{prefix}", method = RequestMethod.GET)
    @ResponseBody
    public String home(@PathVariable("prefix") String prefix) {
        List<Ticket> tickets = ticketRepository.findByMessageLike(prefix + "%");

        String messages = "Tickety rozpoczynajce sie na 'Nic': ";
        for (Ticket ticket : tickets) {
            messages += ticket.getMessage() + " , ";
        }

        //tickets.stream().map(s -> s.getMessage()).collect(
        //        Collectors.joining(" , " , "Tickety: ", ""));

        return messages;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String user(){
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date1 = null;
        Date date2 = null;
        try {
             date1 = formater.parse("2017-04-12 16:32:06");
             date2 = formater.parse("2017-06-13 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<User> users = userRepository.findByDatetimeBetween(date1, date2);
        return users.stream().map(s -> s.getUsername()).collect(
                Collectors.joining(" , ", "Role: ", ""));

    }
}
