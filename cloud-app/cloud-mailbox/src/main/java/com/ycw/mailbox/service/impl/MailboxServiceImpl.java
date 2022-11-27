package com.ycw.mailbox.service.impl;

import com.ycw.common.utils.SpringBeanUtil;
import com.ycw.mailbox.entity.Mailbox;
import com.ycw.mailbox.service.MailboxService;
import lombok.SneakyThrows;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * <p>
 *
 * @Classname MailboxServiceImpl
 * @Description </p>
 */
@Service
@DependsOn("springBeanUtil")
public class MailboxServiceImpl implements MailboxService {
    private final static MongoTemplate mongoTemplate = SpringBeanUtil.getBean(MongoTemplate.class);

    @SneakyThrows
    @Override
    public Mailbox sendMailbox(Mailbox mailbox) {
        mailbox = mongoTemplate.save(mailbox);
        return mailbox;
    }

    @Override
    public List<Mailbox> getMailbox(Long userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Mailbox.class);
    }

    @Override
    public Long collectMailbox(String mailboxId) {
        long collectSuccess = 0;
        Mailbox mailbox = mongoTemplate.findById(mailboxId, Mailbox.class);
        if (mailbox != null) {
            mailbox.setCollectAt(Instant.now());
            mongoTemplate.save(mailbox);
            collectSuccess++;
        }
        return collectSuccess;
    }
}
