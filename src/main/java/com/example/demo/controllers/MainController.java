package com.example.demo.controllers;

import com.example.demo.MailSerivce;
import com.example.demo.TicketRepository;
import com.example.demo.UserRepository;
import com.example.demo.models.Ticket;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

/**
 * Created by OskarPraca on 2017-05-31.
 */

@Controller
public class MainController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailSerivce mailSerivce;

    @Autowired
    TemplateEngine templateEngine;

//    @RequestMapping(value = "/{prefix}", method = RequestMethod.GET)
//    @ResponseBody
//    public String home(@PathVariable("prefix") String prefix) {
//        List<Ticket> tickets = ticketRepository.findByMessageLike(prefix + "%");
//
//        String messages = "Tickety rozpoczynajce sie na 'Nic': ";
//        for (Ticket ticket : tickets) {
//            messages += ticket.getMessage() + " , ";
//        }
//
//        //tickets.stream().map(s -> s.getMessage()).collect(
//        //        Collectors.joining(" , " , "Tickety: ", ""));
//
//        return messages;
//    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String user(){
//        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date date1 = null;
//        Date date2 = null;
//        try {
//             date1 = formater.parse("2017-04-12 16:32:06");
//             date2 = formater.parse("2017-06-13 00:00:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


//        List<User> users = userRepository.findByUsernameContainingAndIdGreaterThan("os", 3);
//        users.stream().map(s -> s.getUsername()).collect(
//                Collectors.joining(" , ", "Role: ", ""))

        Page<User> currentPage = userRepository.findAll(new PageRequest(0, 10));
        StringBuilder builder = new StringBuilder();

        for (User user : currentPage.getContent()) {
            builder.append("Username: " + user.getUsername() + "<br>");
        }

        builder.append("<br><br><br><br>");

        builder.append("<br> Ilość stron: " + currentPage.getTotalPages());
        builder.append("<br> Czy zawiera następną stronę?: " + currentPage.hasNext());
        builder.append("<br> Czy zawiera poprzednią stronę?: " + currentPage.hasPrevious());

        currentPage = userRepository.findAll(currentPage.nextPageable());

        builder.append("<br><br><br><br>");

        for (User user : currentPage.getContent()) {
            builder.append("Username: " + user.getUsername() + "<br>");
        }

        builder.append("<br><br><br><br>");

        builder.append("<br> Ilość stron: " + currentPage.getTotalPages());
        builder.append("<br> Czy zawiera następną stronę?: " + currentPage.hasNext());
        builder.append("<br> Czy zawiera poprzednią stronę?: " + currentPage.hasPrevious());

        return builder.toString();

    }

    @RequestMapping(value = "/mail/{cash}", method = RequestMethod.GET)
    @ResponseBody
    public String email(@PathVariable("cash") int cash) {
        Context context = new Context();
        context.setVariable("welcome", "Witaj Janie Kowalski!");
        context.setVariable("message", "Wisisz nam już " + cash + " zł");

        String bodyHtml = templateEngine.process("emailone", context);

        mailSerivce.sendEmail("***", bodyHtml, "Wysłane z wykładu");
        return "Wysłano maila!";
    }


}
