package connectionFactory;

public class Connection {

    private static final SimpleEntityManager simpleEntityManager = new SimpleEntityManager();

    public static SimpleEntityManager getSimpleEntityManager(){
        return simpleEntityManager;
    }

    public static void shutdown(){
        simpleEntityManager.shutdown();
    }
}
