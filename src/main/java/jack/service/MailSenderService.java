package jack.service;

public interface MailSenderService {
    void sendEmail(String recipient,String subject,String content);
}
