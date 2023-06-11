package qap.io;

import qap.data.MessageRepository;
import qap.domain.Message;
import qap.domain.User;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SwingMainView implements MainView {

    private static final int NUM_COLUMNS_FOR_CONTACTS = 2;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private final MessageRepository messageRepository;

    public SwingMainView(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void showError(Throwable t) {
        JOptionPane.showMessageDialog(null,
                t.getMessage(),
                APP_NAME,
                JOptionPane.ERROR_MESSAGE,
                ICON2);
    }

    @Override
    public void showMainFrame(User user) {
        List<User> contactList = user.getContactList();
        List<Message> allMessagesForMe = messageRepository.getAllMessages(user);
        String messageHistory = allMessagesForMe.stream()
                .sorted(Comparator.comparing(Message::getCreationDate))
                .map(message -> "Сообщение от + " + message.getFrom()
                        + "(" + SDF.format(message.getCreationDate()) + "):"
                        + "\n"
                        + message.getText())
                .collect(Collectors.joining("\n\n"));


        JFrame frame = new JFrame();
        JTextArea textArea = new JTextArea(50, 1);
        JTable contactsTable = new JTable(contactList.size(), NUM_COLUMNS_FOR_CONTACTS);

        textArea.append(messageHistory);
    }

    @Override
    public void startMessaging(User user) {

    }
}
