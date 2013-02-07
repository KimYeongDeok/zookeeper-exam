import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.*;


/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class ServiceWatcher implements Watcher, AsyncCallback.ChildrenCallback{
	private static final String ZOOKEEPER_PATH_SILLYSERVICE = "/server";
	private static final int TIMEOUT = 3000;
	private ZooKeeper zk;


	public ServiceWatcher() throws IOException {
		zk = new ZooKeeper("61.43.139.65:2181", TIMEOUT, this);
	}

    public static void main(String[] args) throws Exception {
   		new ServiceWatcher().watch();
   	}

	public void watch() throws Exception {
		zk.getChildren(ZOOKEEPER_PATH_SILLYSERVICE, true, this, null);
		System.out.println("I'm a watcher, watching your services");

		while (true) {
			Thread.sleep(500);
		}
	}

	@Override
	public void processResult(int arg0, String arg1, Object arg2, List<String> serverPaths) {
        System.out.println("Server Path : "+serverPaths);
	}

	@Override
	public void process(WatchedEvent event) {
        if("NodeChildrenChanged".equals(event.getType().name())){
            System.out.println(event.getState());
            zk.getChildren(ZOOKEEPER_PATH_SILLYSERVICE, true, this, null);
        }
//        System.out.println(event.getPath());
//        System.out.println(event.getType());
//        System.out.println(event.getState());
//        zk.getChildren(ZOOKEEPER_PATH_SILLYSERVICE, true, this, null);
        System.out.println("NodeChildrenChanged".equals(event.getType().name()));
        System.out.println(Event.EventType.NodeChildrenChanged);
        System.out.println(Event.EventType.NodeChildrenChanged==event.getType());
        zk.getChildren(ZOOKEEPER_PATH_SILLYSERVICE, true, this, null);
    }
}

