package com.lstfight.carrieroperatorproxy.controller;

import com.lstfight.carrieroperatorproxy.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lst
 * Created on 2018/5/7.
 */
@RestController
@RequestMapping("cardManner")
public class CardController {
    private CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @RequestMapping("pause")
    public String pauseCard() {
        return "";
    }

    @RequestMapping("/resume")
    public String resumeCard() {
        return "";
    }

    @RequestMapping("query")
    public String queryStatus() {
        return "";
    }


}
