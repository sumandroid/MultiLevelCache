package constants;

public enum Command {
    READ("read"),
    WRITE("write"),
    STAT("stat");

    private String value;

    Command(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Command getFromString(String text){
        for(Command command : Command.values()){
            if(command.value.equalsIgnoreCase(text)){
                return command;
            }
        }
        throw new IllegalArgumentException("Invalid command");
    }
}
