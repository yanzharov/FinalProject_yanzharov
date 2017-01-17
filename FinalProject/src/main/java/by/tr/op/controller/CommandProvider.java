package by.tr.op.controller;

import by.tr.op.command.Command;
import by.tr.op.command.impl.AddNewAnswer;
import by.tr.op.command.impl.AddNewUser;
import by.tr.op.command.impl.AddPoll;
import by.tr.op.command.impl.AddQuestion;
import by.tr.op.command.impl.ChangeLocale;
import by.tr.op.command.impl.DeleteAdminRights;
import by.tr.op.command.impl.DeletePoll;
import by.tr.op.command.impl.DeleteQuestion;
import by.tr.op.command.impl.EditPoll;
import by.tr.op.command.impl.GetImages;
import by.tr.op.command.impl.GetPollById;
import by.tr.op.command.impl.GetPolls;
import by.tr.op.command.impl.GetQuestionById;
import by.tr.op.command.impl.GetUsers;
import by.tr.op.command.impl.SetAdmin;
import by.tr.op.command.impl.ShowHomePage;
import by.tr.op.command.impl.SignIn;
import by.tr.op.command.impl.SignOut;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final CommandProvider INSTANCE = new CommandProvider();
    
    private final static String SIGN_IN="SIGN_IN";
    private final static String SIGN_OUT="SIGN_OUT";
    private final static String ADD_NEW_USER="ADD_NEW_USER";
    private final static String GET_POLLS="GET_POLLS";
    private final static String EDIT_POLL="EDIT_POLL";
    private final static String DELETE_POLL="DELETE_POLL";
    private final static String GET_POLL_BY_ID="GET_POLL_BY_ID";
    private final static String ADD_POLL="ADD_POLL";
    private final static String GET_QUESTION_BY_ID="GET_QUESTION_BY_ID";
    private final static String ADD_NEW_ANSWER="ADD_NEW_ANSWER";
    private final static String CHANGE_LOCALE="CHANGE_LOCALE";
    private final static String SHOW_HOME_PAGE="SHOW_HOME_PAGE";
    private final static String ADD_QUESTION="ADD_QUESTION";
    private final static String DELETE_QUESTION="DELETE_QUESTION";
    private final static String GET_USERS="GET_USERS";
    private final static String SET_ADMIN="SET_ADMIN";
    private final static String DELETE_ADMIN_RIGHTS="DELETE_ADMIN_RIGHTS";
    private final static String GET_IMAGES="GET_IMAGES";
    private Map<String, Command> commands = new HashMap<String, Command>();
	
    private CommandProvider() {
	commands.put(SIGN_IN, new SignIn());
        commands.put(SIGN_OUT, new SignOut());
        commands.put(ADD_NEW_USER, new AddNewUser());
        commands.put(GET_POLLS, new GetPolls());
        commands.put(EDIT_POLL, new EditPoll());
        commands.put(DELETE_POLL, new DeletePoll());
        commands.put(GET_POLL_BY_ID, new GetPollById());
        commands.put(ADD_POLL, new AddPoll());
        commands.put(GET_QUESTION_BY_ID, new GetQuestionById());
        commands.put(ADD_NEW_ANSWER, new AddNewAnswer());
        commands.put(CHANGE_LOCALE, new ChangeLocale());
        commands.put(SHOW_HOME_PAGE, new ShowHomePage());
        commands.put(ADD_QUESTION, new AddQuestion());
        commands.put(DELETE_QUESTION, new DeleteQuestion());
        commands.put(GET_USERS, new GetUsers());
        commands.put(SET_ADMIN, new SetAdmin());
        commands.put(DELETE_ADMIN_RIGHTS, new DeleteAdminRights());
        commands.put(GET_IMAGES, new GetImages());
    }
	
    public Command getCommand(String commandName){
	Command command;
	command = commands.get(commandName);
	return command;
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }
    
    
    
}
