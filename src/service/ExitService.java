package service;

import exception.FailedServiceException;
import startpoint.UserInterface;

import java.util.ResourceBundle;

public class ExitService implements UserRequest {
    /**
     * class performs application exit in case user confirms it by
     * choosing "yes"
     */
    private ResourceBundle rb = ResourceBundle.getBundle("resources.exit");

    @Override
    public void execute() throws FailedServiceException {
        UserInterface.getMessage(rb.getString("exit.question.y.n"));
        String userRequest = UserInterface.readRequest();
        if (userRequest.equals(rb.getString("yes")))
            UserInterface.getMessage(rb.getString("bye.message"));
        UserInterface.requestService();
    }
}