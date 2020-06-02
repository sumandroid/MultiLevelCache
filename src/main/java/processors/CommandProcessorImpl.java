package processors;

import constants.Command;
import services.MultiCacheService;

public class CommandProcessorImpl implements CommandProcessor {

    private MultiCacheService multiCacheService;

    public CommandProcessorImpl(MultiCacheService multiCacheService) {
        this.multiCacheService = multiCacheService;
    }

    @Override
    public String execute(String inputCommand) {
        String[] tokens = inputCommand.split(" ");
        Command command = Command.getFromString(tokens[0]);
        String result = null;
        switch (command) {
            case WRITE:
                if (tokens.length != 3) throw new IllegalArgumentException("Illegal command input for write");
                result = multiCacheService.write(tokens[1], tokens[2]);
                break;
            case READ:
                if(tokens.length != 2) throw new IllegalArgumentException("Illegal command input for read");
                result = multiCacheService.read(tokens[1]);
                break;
            default:
                System.out.println("invalid command");
                break;
        }
        return result;
    }
}
