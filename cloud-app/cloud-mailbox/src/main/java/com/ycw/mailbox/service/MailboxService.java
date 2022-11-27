package com.ycw.mailbox.service;

import com.ycw.common.appendix.Appendix;
import com.ycw.common.enums.MailType;
import com.ycw.mailbox.entity.Mailbox;

import java.util.List;

/**
 * <p>
 *
 * @Classname MailboxService
 * @Description </p>
 */
public interface MailboxService {
    Mailbox sendMailbox(Mailbox mailbox);

    List<Mailbox> getMailbox(Long userId);

    Long collectMailbox(String mailboxId);
}
